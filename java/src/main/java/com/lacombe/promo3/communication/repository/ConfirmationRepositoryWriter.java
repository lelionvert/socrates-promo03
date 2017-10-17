package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.shared.model.Email;

public interface ConfirmationRepositoryWriter {
    void add(Email email);
}
