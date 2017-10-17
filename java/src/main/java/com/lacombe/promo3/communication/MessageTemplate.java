package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;

import java.text.MessageFormat;

public class MessageTemplate {

    public static final Email SENDER = Email.of("houssam@lcdlv.fr");
    public static final String OBJECT = "Confirmation";
    public static final String PATTERN_BODY = "Hello {0},\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih";

    static Message createMessage(Candidate candidate) {

        return Message.MessageBuilder.aMessage()
                    .withSender(SENDER)
                    .withRecipient(candidate.getEmail())
                    .withBody(MessageFormat.format(PATTERN_BODY, candidate.getFirstName()))
                    .withObject(OBJECT)
                    .build();
    }
}
