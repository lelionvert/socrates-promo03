package com.lacombe.promo3.meals;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;
import java.util.Objects;

public class CheckIn {
    private final Email email;
    private final LocalDateTime checkInDate;

    private CheckIn(Email email, LocalDateTime checkInDate) {
        this.email = email;
        this.checkInDate = checkInDate;
    }

    public static CheckIn of(Email email, LocalDateTime checkInDate) {
        return new CheckIn(email, checkInDate);
    }

    boolean hasSameEmail(CheckIn checkIn) {
        return this.email.equals(checkIn.email);
    }

    public Boolean isIncludedIn(Period period) {
        return period.include(checkInDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn checkIn = (CheckIn) o;
        return Objects.equals(email, checkIn.email) &&
                Objects.equals(checkInDate, checkIn.checkInDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, checkInDate);
    }
}
