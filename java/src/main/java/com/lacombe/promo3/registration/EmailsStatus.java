package com.lacombe.promo3.registration;

import com.lacombe.promo3.shared.model.Email;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EmailsStatus {

    private Collection<Email> emailsSent;

    public EmailsStatus(Collection<Email> emailsSent) {
        this.emailsSent = emailsSent;
    }

    public EmailsStatus() {
        emailsSent = new ArrayList<>();
    }

    public boolean isEmpty() {
        return emailsSent.isEmpty();
    }

    public Collection<Email> getEmailsSent() {
        return Collections.unmodifiableCollection(emailsSent);
    }
}
