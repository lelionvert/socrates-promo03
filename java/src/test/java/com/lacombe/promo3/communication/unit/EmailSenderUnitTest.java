package com.lacombe.promo3.communication.unit;

import com.lacombe.promo3.communication.EmailMessage;
import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.EmailSenderDefault;
import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailSenderUnitTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL, "sabine");

    private static final String CYRIL_EMAIL_STRING = "cyril@lcdlv.fr";
    private static final Email CYRIL_EMAIL = Email.of(CYRIL_EMAIL_STRING);
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL, "Cyril");
    private static final EmailMessage CYRIL_MESSAGE = EmailMessage.of().withSender("houssam@lcdlv.fr")
            .withRecipient(CYRIL_EMAIL_STRING)
            .withObject("Confirmation")
            .withCore("Hello Cyril, please confirm or pay.")
            .build();

    @Test
    public void should_know_if_a_single_email_is_correctly_sent() throws Exception {
        EmailSender emailSender = new EmailSenderDefault();

        EmailsStatus emailsStatus = emailSender.send(CYRIL_CANDIDATE);

        assertThat(emailsStatus.getEmailsSent())
                            .containsExactlyInAnyOrder(CYRIL_EMAIL);
    }

    @Test
    public void should_have_a_correct_message_email() throws Exception {
        EmailSender emailSender = new EmailSenderDefault();

        emailSender.send(CYRIL_CANDIDATE);

        assertThat(emailSender.getMessages())
                .containsExactlyInAnyOrder(CYRIL_MESSAGE);
    }


}