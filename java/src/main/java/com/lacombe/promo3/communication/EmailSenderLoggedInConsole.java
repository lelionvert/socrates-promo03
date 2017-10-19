package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class EmailSenderLoggedInConsole implements EmailSenderLogged {

    @Override
    public String printLog() {
        return null;
    }

    @Override
    public EmailMessage getMessage() {
        return null;
    }

    @Override
    public EmailsStatus sendTo(Collection<Candidate> candidates) {
        return null;
    }

    @Override
    public EmailsStatus sendTo(Candidate candidate) {
        /*return EmailMessage.of().withSender("houssam@lcdlv.fr")
                                .withRecipient(candidate.getEmailAddress())
                                .withObject("Confirmation")
                                .withCore("Hello " + candidate.getFirstName() + ", please confirm or pay.")
                                .build();*/

        Email email = candidate.getEmail();
        return new EmailsStatus(Collections.singletonList(email));
    }
}
