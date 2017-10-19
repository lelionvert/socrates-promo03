package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.Collection;

public interface ArchiveEmail {

    void add(Email email);

    Collection<Email> retrieveEmails();

}
