package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;

import java.util.Collection;

public class EmailSenderLoggedInConsole implements EmailSenderLogged {

    @Override
    public String printLog() {
        return null;
    }

    @Override
    public EmailMessage getMessage() {
        return null;
    }

    @Override
    public EmailsStatus sendTo(Collection<Candidate> candidates) {
        return null;
    }
}
