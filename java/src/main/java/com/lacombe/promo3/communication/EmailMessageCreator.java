package com.lacombe.promo3.communication;

import com.lacombe.promo3.shared.model.Candidate;

public class EmailMessageCreator {
    public EmailMessageCreator() {
    }

    EmailMessage getEmailMessage(Candidate candidate) {
        return EmailMessage.of().withSender("houssam@lcdlv.fr")
                .withRecipient(candidate.getEmailAddress())
                .withObject("Confirmation")
                .withCore("Hello " + candidate.getFirstName() + ", please confirm or pay.")
                .build();
    }
}