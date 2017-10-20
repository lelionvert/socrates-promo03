package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.model.Emails;
import com.lacombe.promo3.communication.model.MessageTemplate;
import com.lacombe.promo3.communication.repository.DefaultEmailArchiver;
import com.lacombe.promo3.communication.repository.DefaultLogger;
import com.lacombe.promo3.communication.repository.EmailSender;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationAcceptanceTest {

    private static final String LOG_SABINE_MESSAGE_WAS_SENT = "An email was sent to Email{email='sabine@lcdlv.fr'}";
    private static final String LOG_GABRIEL_MESSAGE_WAS_SENT = "An email was sent to Email{email='gabriel@lcdlv.fr'}";

    private static final Email SABINE_EMAIL_ADDRESS = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL_ADDRESS, "Sabine");
    private static final Email GABRIEL_EMAIL_ADDRESS = Email.of("gabriel@lcdlv.fr");
    private static final Candidate GABRIEL_CANDIDATE = new Candidate(GABRIEL_EMAIL_ADDRESS, "Gabriel");

    private DefaultLogger defaultLogger;
    private DefaultCandidateRepository defaultCandidateRepository;
    private ConfirmationSender confirmationSender;
    private DefaultEmailArchiver defaultArchiveEmail;

    @Mock
    private
    EmailSender emailSender;

    @Test
    public void should_send_email_to_one_candidate() throws Exception {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE);
        defaultLogger = new DefaultLogger();
        defaultArchiveEmail = new DefaultEmailArchiver();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, emailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print()).isEqualTo(LOG_SABINE_MESSAGE_WAS_SENT);
        assertThat(defaultArchiveEmail.retrieveEmails()).isEqualTo(Emails.with(SABINE_EMAIL_ADDRESS));
        Mockito.verify(emailSender, times(1)).send(MessageTemplate.createMessage(SABINE_CANDIDATE));
    }

    @Test
    public void should_send_email_to_the_only_candidate_that_did_not_receive_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT);
        defaultArchiveEmail = new DefaultEmailArchiver(Emails.with(SABINE_EMAIL_ADDRESS));
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, emailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        EmailStatus emailStatus = confirmationSender.send();

        //THEN
        assertThat(defaultLogger.print())
            .containsOnlyOnce(LOG_SABINE_MESSAGE_WAS_SENT)
            .containsOnlyOnce(LOG_GABRIEL_MESSAGE_WAS_SENT);
        assertThat(defaultArchiveEmail.retrieveEmails()).isEqualTo(Emails.with(SABINE_EMAIL_ADDRESS, GABRIEL_EMAIL_ADDRESS));
        Mockito.verify(emailSender, times(1)).send(MessageTemplate.createMessage(GABRIEL_CANDIDATE));
        Mockito.verify(emailSender, never()).send(MessageTemplate.createMessage(SABINE_CANDIDATE));
        Assertions.assertThat(emailStatus).isEqualTo(EmailStatus.ALL_EMAIL_SENT);

    }

    @Test
    public void should_send_a_warning_message_when_all_candidates_already_received_the_message() {
        //GIVEN
        defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, GABRIEL_CANDIDATE);
        defaultArchiveEmail = new DefaultEmailArchiver(Emails.with(SABINE_EMAIL_ADDRESS, GABRIEL_EMAIL_ADDRESS));
        defaultLogger = DefaultLogger.with(LOG_SABINE_MESSAGE_WAS_SENT, LOG_GABRIEL_MESSAGE_WAS_SENT);
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, emailSender, defaultLogger, defaultArchiveEmail);

        //WHEN
        EmailStatus message = confirmationSender.send();

        //THEN
        Assertions.assertThat(message).isEqualTo(EmailStatus.NO_EMAIL_SENT);
    }

}
