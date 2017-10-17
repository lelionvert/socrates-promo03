package com.lacombe.promo3.communication;

public class EmailMessage {

    private String sender;
    private String recipient;
    private String object;
    private String core;

    public static EmailMessageBuilder of() {
        return new EmailMessageBuilder();
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
