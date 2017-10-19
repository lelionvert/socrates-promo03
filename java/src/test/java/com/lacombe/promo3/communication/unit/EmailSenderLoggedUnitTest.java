package com.lacombe.promo3.communication.unit;

import com.lacombe.promo3.communication.EmailSenderLogged;
import com.lacombe.promo3.communication.EmailSenderLoggedInConsole;
import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class EmailSenderLoggedUnitTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);

    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);

    @Test
    public void should_send_an_email_correctly_to_anyone() throws Exception {
        EmailSenderLogged emailSender = new EmailSenderLoggedInConsole();

        EmailsStatus emailsStatus = emailSender.sendTo(CYRIL_CANDIDATE);

        assertThat(emailsStatus.getEmailsSent())
                            .containsExactlyInAnyOrder(CYRIL_EMAIL);
    }

}