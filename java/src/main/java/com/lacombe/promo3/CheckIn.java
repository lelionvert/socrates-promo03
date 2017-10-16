package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;
import java.time.Month;

class CheckIn {
    private final Email email;
    private LocalDateTime checkingDate;

    CheckIn(Email email) {
        this.email = email;
    }

    void setCheckingDate(LocalDateTime checkingDate) {
        this.checkingDate = checkingDate;
    }

    boolean isLate() {
        return  checkingDate != null && checkingDate.isAfter(LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0,0));
    }

    public LocalDateTime getCheckInDate() {
        return checkingDate;
    }
}
