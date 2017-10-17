package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import org.junit.Test;

import static com.lacombe.promo3.communication.Message.MessageBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailSenderTest {

    private static final Email CYRIL_EMAIL_ADDRESS = Email.of("cyril@lcdlv.fr");
    private static final Email VALENTIN_EMAIL_ADDRESS = Email.of("valentin@lcdlv.fr");
    private static final String HOUSSAM_EMAIL_ADDRESS = "houssam@lcdlv.fr";

    @Test
    public void should_add_cyril_message_to_an_empty_list_of_emails_sent() {
        //GIVEN
        DefaultEmailSender defaultEmailSender = new DefaultEmailSender();

        Message cyrilMessage = MessageBuilder.aMessage().withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(CYRIL_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        //WHEN
        defaultEmailSender.send(cyrilMessage);

        //THEN
        assertThat(defaultEmailSender.getMessagesSent()).hasSize(1).contains(cyrilMessage);
    }

    @Test
    public void should_add_valentin_message_to_a_list_of_emails_sent_with_already_one_message() {
        //GIVEN
        Message cyrilMessage = MessageBuilder.aMessage().withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(CYRIL_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        DefaultEmailSender defaultEmailSender = DefaultEmailSender.with(cyrilMessage);

        //WHEN
        Message valentinMessage = MessageBuilder.aMessage().withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(VALENTIN_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        defaultEmailSender.send(valentinMessage);

        //THEN
        assertThat(defaultEmailSender.getMessagesSent()).hasSize(2).containsExactlyInAnyOrder(cyrilMessage, valentinMessage);
    }
}
