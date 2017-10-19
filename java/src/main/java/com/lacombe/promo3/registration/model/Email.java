package com.lacombe.promo3.registration.model;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private static final Predicate<String> isEmptyOrNull = s -> s == null || s.isEmpty();

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String email;

    public static Email valueOf(String email) {
        verifyEmail(email);
        return new Email(email);
    }

    public Email(String email){
        this.email = email;
    }

    private static void verifyEmail(String email) {
        if(isEmptyOrNull.test(email)
            || !isValidFormat(email)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isValidFormat(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        return email != null ? email.equals(email1.email) : email1.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return email;
    }
}
