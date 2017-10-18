package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommunicationAcceptanceTest {

    private static final String LOG_SABINE_MESSAGE_WAS_SENT = "An email was sent to Email{email='sabine@lcdlv.fr'}";
    private static final String LOG_GABRIEL_MESSAGE_WAS_SENT = "An email was sent to Email{email='gabriel@lcdlv.fr'}";

    private static final Candidate SABINE_CANDIDATE = new Candidate(Email.of("sabine@lcdlv.fr"), "Sabine");
    private static final Candidate GABRIEL_CANDIDATE = new Candidate(Email.of("gabriel@lcdlv.fr"), "Gabriel");

    private static DefaultEmailSender defaultEmailSender;
    private static DefaultLogger defaultLogger;
    private static DefaultCandidateRepository defaultCandidateRepository;
    private static ConfirmationSender confirmationSender;

    @Test
    public void should_send_email_to_one_candidate() throws Exception {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE);
        defaultEmailSender = new DefaultEmailSender();
        defaultLogger = new DefaultLogger();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger);

        //WHEN
        confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print()).isEqualTo(LOG_SABINE_MESSAGE_WAS_SENT);
        assertThat(defaultEmailSender.getMessagesSent())
            .hasSize(1)
            .contains(MessageTemplate.createMessage(SABINE_CANDIDATE));
    }

    @Test
    public void should_send_email_to_the_only_candidate_that_did_not_receive_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultEmailSender = DefaultEmailSender.with(MessageTemplate.createMessage(SABINE_CANDIDATE));
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT);
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger);

        //WHEN
        confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print())
            .containsOnlyOnce(LOG_SABINE_MESSAGE_WAS_SENT)
            .containsOnlyOnce(LOG_GABRIEL_MESSAGE_WAS_SENT);
        assertThat(defaultEmailSender.getMessagesSent())
            .hasSize(2)
            .containsExactlyInAnyOrder(MessageTemplate.createMessage(SABINE_CANDIDATE), MessageTemplate.createMessage(GABRIEL_CANDIDATE));
    }

    @Test
    public void should_send_a_warning_message_when_all_candidates_already_received_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultEmailSender = DefaultEmailSender.with(MessageTemplate.createMessage(SABINE_CANDIDATE), MessageTemplate.createMessage(GABRIEL_CANDIDATE));
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT, LOG_GABRIEL_MESSAGE_WAS_SENT);
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger);

        //WHEN
        confirmationSender.send();

        //THEN
    }

}
