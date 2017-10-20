package com.lacombe.promo3.meals;

import com.lacombe.promo3.registration.model.Email;

import java.time.LocalDateTime;

class MealsUtils {
    static CheckIn checkInWithoutDate(String email) {
        return CheckIn.of(Email.of(email), null);
    }

    static CheckIn checkInOnFirstDay(int hour, int minute, String email) {
        return CheckIn.of(Email.of(email),
                LocalDateTime.of(2017, 10, 27, hour, minute, 0, 0));
    }
}
