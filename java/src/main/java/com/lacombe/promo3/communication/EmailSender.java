package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;

import java.util.Collection;

public interface EmailSender {

    EmailsStatus sendTo(Collection<Candidate> candidates);

    EmailsStatus sendTo(Candidate candidate);
}
