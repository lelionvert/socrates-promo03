package com.lacombe.promo3.communication;

import com.github.sleroy.fakesmtp.core.ServerConfiguration;
import com.github.sleroy.junit.mail.server.test.FakeSmtpRule;
import com.lacombe.promo3.communication.model.EmailMessage;
import com.lacombe.promo3.communication.repository.SMTPEmailSender;
import com.lacombe.promo3.registration.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.Properties;

public class SMTPEmailSenderIntegrationTest {

    private static final Email HOUSSAM_EMAIL = Email.of("houssam@lcdlv.fr");

    private static final String SABINE_EMAIL_STRING = "sabine.ferreira@gmail.com";
    private static final Email SABINE_EMAIL_ADDRESS = Email.of(SABINE_EMAIL_STRING);
    private static final EmailMessage SABINE_EMAIL_MESSAGE = EmailMessage.MessageBuilder.aMessage()
                                                                .withSender(HOUSSAM_EMAIL)
                                                                .withRecipient(SABINE_EMAIL_ADDRESS)
                                                                .withObject("Confirmation")
                                                                .withBody("test")
                                                                .build();
    @Rule
    public final FakeSmtpRule smtpServer = new FakeSmtpRule(ServerConfiguration.create().port(2525).charset("UTF-8"));

    @Test
    public void should_send_an_email() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "2525");

        smtpServer.getServerConfiguration().relayDomains("gmail.com");
        SMTPEmailSender smtpEmailSender = new SMTPEmailSender(properties);
        smtpEmailSender.send(SABINE_EMAIL_MESSAGE);

        Assertions.assertThat(smtpServer.mailBox().isEmpty()).isFalse();
    }
}
