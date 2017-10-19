package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EmailSenderDefault implements EmailSender {

    private Collection<EmailMessage> messages = new ArrayList<>();

    public Collection<EmailMessage> getMessages() {
        return messages;
    }

    @Override
    public EmailsStatus sendToMany(Collection<Candidate> candidates) {
        Collection<EmailsStatus> emailsStatus = new ArrayList<>();
        candidates.forEach( candidate -> {
                    EmailsStatus emailStatus = sendToOne(candidate);
                    emailsStatus.add(emailStatus);
                }
        );
        return null;
    }

    @Override
    public EmailsStatus sendToOne(Candidate candidate) {
        EmailMessage confirmationMessage = getEmailMessage(candidate);
        /*TODO Send the email*/

        this.messages.add(confirmationMessage);

        Email email = candidate.getEmail();
        return new EmailsStatus(Collections.singletonList(email));
    }

    private EmailMessage getEmailMessage(Candidate candidate) {
        return EmailMessage.of().withSender("houssam@lcdlv.fr")
                    .withRecipient(candidate.getEmailAddress())
                    .withObject("Confirmation")
                    .withCore("Hello " + candidate.getFirstName() + ", please confirm or pay.")
                    .build();
    }
}
