package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CommunicationAcceptanceTest {


    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final String SABINE = "Sabine";
    private static final String MESSAGE_LOGGED_WHEN_SENT = "An email was sent to Email{email='sabine@lcdlv.fr'}";

    @Test
    public void should_send_email_to_one_candidate() throws Exception {
        //GIVEN
        final Candidate sabineCandidate = new Candidate(SABINE_EMAIL, SABINE);
        final DefaultCandidateRepository defaultCandidateRepository = DefaultCandidateRepository.withExisting(sabineCandidate);
        final DefaultEmailSender defaultEmailSender = new DefaultEmailSender();
        final DefaultLogger defaultLogger = new DefaultLogger();
        ConfirmationSender confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger);

        //WHEN
        confirmationSender.send();

        //THEN
        Assertions.assertThat(defaultLogger.print()).isEqualTo(MESSAGE_LOGGED_WHEN_SENT);
        Assertions.assertThat(defaultEmailSender.getMessagesSent()).hasSize(1)
            .contains(MessageTemplate.createMessage(sabineCandidate));
    }
}
