package com.lacombe.promo3.communication.model;

import com.lacombe.promo3.registration.model.Email;

public class EmailMessage {

    private Email sender;
    private Email recipient;
    private String object;
    private String body;

    private EmailMessage(Email sender, Email recipient, String object, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.object = object;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public Email getSender() {
        return sender;
    }

    public String getObject() {
        return object;
    }

    public static final class MessageBuilder {
        private Email sender;
        private Email recipient;
        private String object;
        private String body;

        private MessageBuilder() {
        }

        public static MessageBuilder aMessage() {
            return new MessageBuilder();
        }

        public MessageBuilder withSender(Email sender) {
            this.sender = sender;
            return this;
        }

        public MessageBuilder withRecipient(Email recipient) {
            this.recipient = recipient;
            return this;
        }

        public MessageBuilder withObject(String object) {
            this.object = object;
            return this;
        }

        public MessageBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(sender, recipient, object, body);
        }
    }

    public Email getRecipient() {
        return recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailMessage emailMessage = (EmailMessage) o;

        if (sender != null ? !sender.equals(emailMessage.sender) : emailMessage.sender != null) return false;
        if (recipient != null ? !recipient.equals(emailMessage.recipient) : emailMessage.recipient != null) return false;
        if (object != null ? !object.equals(emailMessage.object) : emailMessage.object != null) return false;
        return body != null ? body.equals(emailMessage.body) : emailMessage.body == null;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
