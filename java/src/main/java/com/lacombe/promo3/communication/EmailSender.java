package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;

import java.util.Collection;

public interface EmailSender {

    EmailsStatus sendToMany(Collection<Candidate> candidates);

    EmailsStatus sendToOne(Candidate candidate);

    Collection<EmailMessage> getMessages();
}
