package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommunicationAcceptanceTest {

    private static final String LOG_SABINE_MESSAGE_WAS_SENT = "An email was sent to Email{email='sabine@lcdlv.fr'}";
    private static final String LOG_GABRIEL_MESSAGE_WAS_SENT = "An email was sent to Email{email='gabriel@lcdlv.fr'}";

    public static final Email SABINE_EMAIL_ADDRESS = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL_ADDRESS, "Sabine");
    public static final Email GABRIEL_EMAIL_ADDRESS = Email.of("gabriel@lcdlv.fr");
    private static final Candidate GABRIEL_CANDIDATE = new Candidate(GABRIEL_EMAIL_ADDRESS, "Gabriel");

    private DefaultEmailSender defaultEmailSender;
    private DefaultLogger defaultLogger;
    private DefaultCandidateRepository defaultCandidateRepository;
    private ConfirmationSender confirmationSender;
    private DefaultArchiveEmail defaultArchiveEmail;

    @Test
    public void should_send_email_to_one_candidate() throws Exception {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE);
        defaultEmailSender = new DefaultEmailSender();
        defaultLogger = new DefaultLogger();
        defaultArchiveEmail = new DefaultArchiveEmail();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print()).isEqualTo(LOG_SABINE_MESSAGE_WAS_SENT);
        assertThat(defaultArchiveEmail.retrieveEmails())
            .hasSize(1)
            .contains(SABINE_EMAIL_ADDRESS);
    }

    @Test
    public void should_send_email_to_the_only_candidate_that_did_not_receive_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultEmailSender = new DefaultEmailSender();
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT);
        defaultArchiveEmail = DefaultArchiveEmail.with(SABINE_EMAIL_ADDRESS);
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print())
            .containsOnlyOnce(LOG_SABINE_MESSAGE_WAS_SENT)
            .containsOnlyOnce(LOG_GABRIEL_MESSAGE_WAS_SENT);
        assertThat(defaultArchiveEmail.retrieveEmails())
            .hasSize(2)
            .containsExactlyInAnyOrder(SABINE_EMAIL_ADDRESS, GABRIEL_EMAIL_ADDRESS);
    }

    @Test
    public void should_send_a_warning_message_when_all_candidates_already_received_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultEmailSender = new DefaultEmailSender();
        defaultArchiveEmail = DefaultArchiveEmail.with(SABINE_EMAIL_ADDRESS, GABRIEL_EMAIL_ADDRESS);
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT, LOG_GABRIEL_MESSAGE_WAS_SENT);
        DefaultArchiveEmail defaultArchiveEmail = new DefaultArchiveEmail();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        confirmationSender.send();

        //THEN
    }

}
