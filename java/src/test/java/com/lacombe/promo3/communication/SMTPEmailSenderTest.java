package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.model.EmailMessage;
import com.lacombe.promo3.communication.repository.SMTPEmailSender;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.junit.Ignore;
import org.junit.Test;

public class SMTPEmailSenderTest {

    private static final Email HOUSSAM_EMAIL = Email.of("houssam@lcdlv.fr");

    private static final String SABINE_EMAIL_STRING = "melody.scholtes@gmail.com";
    public static final Email SABINE_EMAIL_ADDRESS = Email.of(SABINE_EMAIL_STRING);
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL_ADDRESS, "Sabine");
    private static final EmailMessage SABINE_EMAIL_MESSAGE = EmailMessage.MessageBuilder.aMessage()
                                                                .withSender(HOUSSAM_EMAIL)
                                                                .withRecipient(SABINE_EMAIL_ADDRESS)
                                                                .withObject("Confirmation")
                                                                .withBody("test")
                                                                .build();

    @Ignore
    @Test
    public void should_send_an_email() throws Exception {
        SMTPEmailSender smtpEmailSender = new SMTPEmailSender();
        smtpEmailSender.send(SABINE_EMAIL_MESSAGE);
    }
}
