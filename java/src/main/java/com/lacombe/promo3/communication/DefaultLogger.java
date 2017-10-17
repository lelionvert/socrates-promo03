package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultLogger implements Logger{

    public static final String CARRIAGE_RETURN_DELIMITER = "\n";
    public static final String PATTERN_LOG_WHEN_EMAIL_SENT = "An email was sent to {0}";

    Collection<String> messagesLog = new ArrayList<>();

    public String print() {
        return String.join(CARRIAGE_RETURN_DELIMITER, messagesLog);
    }

    @Override
    public void log(Email emailAddress) {

        messagesLog.add(MessageFormat.format(PATTERN_LOG_WHEN_EMAIL_SENT,emailAddress.toString()));
    }
}
