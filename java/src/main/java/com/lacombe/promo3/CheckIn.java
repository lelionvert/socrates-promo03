package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;
import java.time.Month;

public class CheckIn {
    private Email email;
    private LocalDateTime checkingDate;

    CheckIn(Email email, LocalDateTime checkingDate) {
        this.email = email;
        this.checkingDate = checkingDate;
    }

    CheckIn(Email email) {
        this.email = email;
    }

    public void setCheckingDate(LocalDateTime checkingDate) {
        this.checkingDate = checkingDate;
    }

    public boolean isLate() {
        return checkingDate.isAfter(LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0,0));
    }
}
