package com.lacombe.promo3.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class DefaultEmailSender implements EmailSender {

    private final Collection<Message> messagesSent;

    public DefaultEmailSender() {
        this.messagesSent = new ArrayList<>();
    }

    private DefaultEmailSender(Collection<Message> messagesSent) {
        this();
        this.messagesSent.addAll(messagesSent);
    }

    public static DefaultEmailSender with(Message... messages) {
        return new DefaultEmailSender(Arrays.asList(messages));
    }


    public Collection<Message> getMessagesSent() {
        return Collections.unmodifiableCollection(messagesSent);
    }

    @Override
    public void send(Message message) {
        messagesSent.add(message);
    }
}
