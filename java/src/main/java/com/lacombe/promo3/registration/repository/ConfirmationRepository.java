package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Email;

import java.util.Collection;

public interface ConfirmationRepository {
    Collection<Email> getEmails();
}
