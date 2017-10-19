package com.lacombe.promo3.communication.model;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;

import java.text.MessageFormat;

public class MessageTemplate {

    public static final Email SENDER = Email.of("houssam@lcdlv.fr");
    public static final String OBJECT = "Confirmation";
    public static final String PATTERN_BODY = "Hello {0},\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih";

    public static EmailMessage createMessage(Candidate candidate) {

        final String candidateFirstName = candidate.getFirstName();
        final String formatBody = MessageFormat.format(PATTERN_BODY, candidateFirstName);

        return EmailMessage.MessageBuilder.aMessage()
                    .withSender(SENDER)
                    .withRecipient(candidate.getEmail())
                    .withBody(formatBody)
                    .withObject(OBJECT)
                    .build();
    }
}
