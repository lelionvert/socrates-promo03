package com.lacombe.promo3.communication;

public interface EmailSenderLogged extends EmailSender {

    String printLog();

    EmailMessage getMessage();
}
