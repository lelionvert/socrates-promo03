package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Candidates;
import com.lacombe.promo3.shared.model.Email;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EmailSenderDefault implements EmailSender {

    private final EmailMessageCreator emailMessageCreator = new EmailMessageCreator();
    private Collection<EmailMessage> messages = new ArrayList<>();

    public Collection<EmailMessage> getMessages() {
        return messages;
    }

    @Override
    public EmailsStatus sendToMany(Candidates candidates) {
        Collection<EmailsStatus> emailsStatus = new ArrayList<>();
        candidates.forEach( candidate -> {
                    EmailsStatus emailStatus = send(candidate);
                    emailsStatus.add(emailStatus);
                }
        );
        return null;
    }

    @Override
    public EmailsStatus send(Candidate candidate) {
        EmailMessage confirmationMessage = emailMessageCreator.getEmailMessage(candidate);
        /*TODO Send the email*/

        this.messages.add(confirmationMessage);

        Email email = candidate.getEmail();
        return new EmailsStatus(Collections.singletonList(email));
    }
}
