package com.lacombe.promo3.meals;

import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;

class CheckIn {
    private final Email email;
    private final LocalDateTime checkInDate;

    private CheckIn(Email email, LocalDateTime checkInDate) {
        this.email = email;
        this.checkInDate = checkInDate;
    }

    public static CheckIn of(Email email, LocalDateTime checkInDate) {
        return new CheckIn(email, checkInDate);
    }

    boolean isBetween(LocalDateTime begin, LocalDateTime end) {
       return checkInDate != null
                && checkInDate.isAfter(begin)
                && checkInDate.isBefore(end);
    }
}
