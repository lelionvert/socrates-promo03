package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.communication.model.Emails;
import com.lacombe.promo3.registration.model.Email;

public class DefaultEmailArchiver implements EmailArchiver {

    private Emails emails;

    public DefaultEmailArchiver() {
        this.emails = new Emails();
    }

    public DefaultEmailArchiver(Emails emails) {
        this();
        this.emails = emails;
    }

    @Override
    public void add(Email email) {
        emails.add(email);
    }

    @Override
    public Emails retrieveEmails() {
        return emails;
    }
}
