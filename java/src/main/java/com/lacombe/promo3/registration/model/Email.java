package com.lacombe.promo3.registration.model;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private static final Predicate<String> isEmptyOrNull = s -> s == null || s.isEmpty();

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String emailString;

    public static Email of(String email) {
        verifyEmail(email);
        return new Email(email);
    }

    public String getEmailString() {
        return emailString;
    }

    private Email(String email){
        this.emailString = email;
    }

    private static void verifyEmail(String email) {
        if(isEmptyOrNull.test(email)
            || !isValidFormat(email)) {
            throw new IllegalArgumentException(email);
        }
    }

    private static boolean isValidFormat(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        return emailString != null ? emailString.equals(email1.emailString) : email1.emailString == null;
    }

    @Override
    public int hashCode() {
        return emailString != null ? emailString.hashCode() : 0;
    }

    @Override
    public String toString() {
        return emailString;
    }
}
