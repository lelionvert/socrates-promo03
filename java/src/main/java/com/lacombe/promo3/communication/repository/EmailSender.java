package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.communication.model.Message;

public interface EmailSender {

    void send(Message message);
}
