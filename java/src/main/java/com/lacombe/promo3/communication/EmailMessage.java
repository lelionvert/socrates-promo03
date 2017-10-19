package com.lacombe.promo3.communication;

public class EmailMessage {

    private String sender;
    private String recipient;
    private String object;
    private String core;

    public static EmailMessageBuilder of() {
        return new EmailMessageBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailMessage that = (EmailMessage) o;

        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        if (object != null ? !object.equals(that.object) : that.object != null) return false;
        return core != null ? core.equals(that.core) : that.core == null;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (core != null ? core.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", object='" + object + '\'' +
                ", core='" + core + '\'' +
                '}';
    }

    public static final class EmailMessageBuilder {
        private String sender;
        private String recipient;
        private String object;
        private String core;

        private EmailMessageBuilder() {
        }

        public EmailMessageBuilder withSender(String sender) {
            this.sender = sender;
            return this;
        }

        public EmailMessageBuilder withRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public EmailMessageBuilder withObject(String object) {
            this.object = object;
            return this;
        }

        public EmailMessageBuilder withCore(String core) {
            this.core = core;
            return this;
        }

        public EmailMessage build() {
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.sender = this.sender;
            emailMessage.object = this.object;
            emailMessage.recipient = this.recipient;
            emailMessage.core = this.core;
            return emailMessage;
        }
    }
}
