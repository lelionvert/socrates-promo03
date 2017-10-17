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



    static CheckIn of(Email email, LocalDateTime checkInDate) {
        return new CheckIn(email, checkInDate);
    }

    static CheckIn of(Email email) {
        return new CheckIn(email);
    }


    void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    boolean isLate() {
        return checkInDate != null
                && checkInDate.isAfter(LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0,0))
                && checkInDate.isBefore(LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0));
    }

}
