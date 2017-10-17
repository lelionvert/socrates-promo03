package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;
import java.time.Month;

class CheckIn {
    private final Email email;
    private LocalDateTime checkInDate;

    private CheckIn(Email email) {
        this(email, null);
    }

    private CheckIn(Email email, LocalDateTime checkInDate) {
        this.email = email;
        this.checkInDate = checkInDate;
    }

    static CheckIn of(Email email) {
        return new CheckIn(email);
    }

    static CheckIn of(Email email, LocalDateTime checkInDate) {
        return new CheckIn(email, checkInDate);
    }

    void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    boolean isBetween(LocalDateTime begin, LocalDateTime end) {
       return checkInDate != null
                && checkInDate.isAfter(begin)
                && checkInDate.isBefore(end);
    }
}
