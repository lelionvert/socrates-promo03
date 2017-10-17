package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailSenderTest {

    private static final Email CYRIL_EMAIL_ADDRESS = Email.of("cyril@lcdlv.fr");
    private static final Email VALENTIN_EMAIL_ADDRESS = Email.of("valentin@lcdlv.fr");
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL_ADDRESS, "Cyril");
    private static final Candidate VALENTIN_CANDIDATE = new Candidate(VALENTIN_EMAIL_ADDRESS, "Valentin");

    @Test
    public void should_add_cyril_message_to_an_empty_list_of_emails_sent() {
        //GIVEN
        DefaultEmailSender defaultEmailSender = new DefaultEmailSender();

        //WHEN
        defaultEmailSender.send(MessageTemplate.createMessage(CYRIL_CANDIDATE));

        //THEN
        assertThat(defaultEmailSender.getMessagesSent())
            .hasSize(1)
            .contains(MessageTemplate.createMessage(CYRIL_CANDIDATE));
    }

    @Test
    public void should_add_valentin_message_to_a_list_of_emails_sent_with_already_one_message() {
        //GIVEN

        DefaultEmailSender defaultEmailSender = DefaultEmailSender.with(MessageTemplate.createMessage(CYRIL_CANDIDATE));

        //WHEN
        defaultEmailSender.send(MessageTemplate.createMessage(VALENTIN_CANDIDATE));

        //THEN
        assertThat(defaultEmailSender.getMessagesSent())
            .hasSize(2)
            .containsExactlyInAnyOrder(MessageTemplate.createMessage(CYRIL_CANDIDATE), MessageTemplate.createMessage(VALENTIN_CANDIDATE));
    }
}
