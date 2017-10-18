package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.Collection;

public interface EmailSender {

    void send(Message message);

    Collection<Email> getEmailsAlreadyUsedForConfirmationEmail();
}
