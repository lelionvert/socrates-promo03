package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.*;

public class DefaultArchiveEmail implements ArchiveEmail {

    private List<Email> emails;

    DefaultArchiveEmail() {
        this.emails = new ArrayList<>();
    }

    public DefaultArchiveEmail(List<Email> emails) {
        this();
        this.emails.addAll(emails);
    }

    public static DefaultArchiveEmail with(Email... emails) {
        return new DefaultArchiveEmail(Arrays.asList(emails));
    }

    @Override
    public void add(Email email) {
        emails.add(email);
    }

    @Override
    public Collection<Email> retrieveEmails() {
        return Collections.unmodifiableCollection(emails);
    }
}
