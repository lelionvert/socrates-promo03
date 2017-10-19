package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.*;

public class DefaultEmailArchiver implements EmailArchiver {

    private Emails emails;

    DefaultEmailArchiver() {
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
    public Collection<Email> retrieveEmails() {
        return emails.retrieveEmails();
    }
}
