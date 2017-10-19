package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailArchiverTest {

    private static final Email CYRIL_EMAIL_ADDRESS = Email.of("cyril@lcdlv.fr");
    private static final Email VALENTIN_EMAIL_ADDRESS = Email.of("valentin@lcdlv.fr");

    @Test
    public void should_add_cyril_email_to_an_empty_list_of_emails_sent() {
        //GIVEN
        DefaultEmailArchiver defaultArchiveEmail = new DefaultEmailArchiver();

        //WHEN
        defaultArchiveEmail.add(CYRIL_EMAIL_ADDRESS);

        //THEN
        assertThat(defaultArchiveEmail.retrieveEmails())
            .hasSize(1)
            .contains(CYRIL_EMAIL_ADDRESS);
    }

    @Test
    public void should_add_valentin_message_to_a_list_of_emails_sent_with_already_one_message() {
        //GIVEN
        DefaultEmailArchiver defaultArchiveEmail = new DefaultEmailArchiver(Emails.with(CYRIL_EMAIL_ADDRESS));

        //WHEN
        defaultArchiveEmail.add(VALENTIN_EMAIL_ADDRESS);

        //THEN
        assertThat(defaultArchiveEmail.retrieveEmails())
            .hasSize(2)
            .containsExactlyInAnyOrder(CYRIL_EMAIL_ADDRESS, VALENTIN_EMAIL_ADDRESS);
    }
}
