package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;

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
        throw new NotImplementedException();
    }
}
