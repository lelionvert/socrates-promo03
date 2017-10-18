package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.*;

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

    @Override
    public Collection<Email> getEmailsAlreadyUsedForConfirmationEmail() {
        Collection<Email> emails = new ArrayList<>();
        for(Message message : messagesSent) {
            emails.add(message.getRecipient());
        }
        return emails;
    }
}
