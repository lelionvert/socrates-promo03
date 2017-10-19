package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.communication.model.EmailMessage;

public interface EmailSender {

    void send(EmailMessage emailMessage);
}
