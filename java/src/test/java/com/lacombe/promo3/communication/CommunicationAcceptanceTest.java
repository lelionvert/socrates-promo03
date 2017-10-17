package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CommunicationAcceptanceTest {



    @Test
    public void should_send_email_to_one_candidate() throws Exception {
        //given
        final Candidate sabineCandidate = new Candidate(Email.of("sabine@lcdlv.fr"));
        final DefaultCandidateRepository defaultCandidateRepository = DefaultCandidateRepository.withExisting(sabineCandidate);
        final DefaultEmailSender defaultEmailSender = new DefaultEmailSender();
        final DefaultLogger defaultLogger = new DefaultLogger();
        ConfirmationSender confirmationSender = new ConfirmationSender(defaultCandidateRepository, defaultEmailSender, defaultLogger);

        //when
        confirmationSender.send();

        //then
        Assertions.assertThat(defaultLogger.print()).isEqualTo("An email was sent to Email{email='sabine@lcdlv.fr'}");
        Assertions.assertThat(defaultEmailSender.getMessagesSent()).hasSize(1)
            .contains(Message.MessageBuilder.aMessage()
                .withSender("houssam@lcdlv.fr")
                .withRecipient(Email.of("sabine@lcdlv.fr"))
                .withObject("Confirmation")
                .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
                .build());
    }
}
