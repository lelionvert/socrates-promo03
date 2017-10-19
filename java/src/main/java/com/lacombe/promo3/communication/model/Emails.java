package com.lacombe.promo3.communication.model;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;

import java.util.*;

public class Emails {

    private List<Email> emails;

    public Emails() {
        this.emails = new ArrayList<Email>();
    }

    private Emails(List<Email> emails) {
        this();
        this.emails.addAll(emails);
    }

    public static Emails with(Email... emails) {
        return new Emails(Arrays.asList(emails));
    }

    public void add(Email email) {
        emails.add(email);
    }

    public boolean contains(Email email) {
        return this.emails.contains(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emails emails1 = (Emails) o;

        return emails != null ? emails.equals(emails1.emails) : emails1.emails == null;
    }

    @Override
    public int hashCode() {
        return emails != null ? emails.hashCode() : 0;
    }
}