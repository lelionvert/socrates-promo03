package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Candidates;

import java.util.Collection;

public interface EmailSender {

    EmailsStatus sendToMany(Candidates candidates);

    EmailsStatus send(Candidate candidate);

    Collection<EmailMessage> getMessages();
}
