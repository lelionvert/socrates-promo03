package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultLogger implements Logger{

    Collection<String> messagesLog = new ArrayList<>();

    public String print() {
        return String.join("\n", messagesLog);
    }

    @Override
    public void log(Email emailAddress) {
        messagesLog.add("An email was sent to "+emailAddress.toString());
    }
}
