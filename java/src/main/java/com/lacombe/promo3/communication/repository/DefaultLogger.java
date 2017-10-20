package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.registration.model.Email;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DefaultLogger implements Logger{

    private static final String CARRIAGE_RETURN_DELIMITER = "\n";
    private static final String PATTERN_LOG_WHEN_MESSAGE_SENT = "An email was sent to {0}";

    private final Collection<String> messagesLog;

    public DefaultLogger() {
        this.messagesLog = new ArrayList<>();
    }

    private DefaultLogger(Collection<String> messagesLog) {
        this();
        this.messagesLog.addAll(messagesLog);
    }

    public static DefaultLogger with(String... messagesLog) {
        return new DefaultLogger(Arrays.asList(messagesLog));
    }

    public String print() {
        return String.join(CARRIAGE_RETURN_DELIMITER, messagesLog);
    }

    @Override
    public void log(Email emailAddress) {

        final String emailToPrint = emailAddress.toString();
        final String formatMessage = MessageFormat.format(PATTERN_LOG_WHEN_MESSAGE_SENT, emailToPrint);
        messagesLog.add(formatMessage);
    }
}
