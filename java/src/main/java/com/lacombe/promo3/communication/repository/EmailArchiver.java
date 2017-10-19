package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.communication.model.Emails;
import com.lacombe.promo3.registration.model.Email;

public interface EmailArchiver {

    void add(Email email);

    Emails retrieveEmails();

}
