package com.lacombe.promo3;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

class ColdMealsCounter {
    static final LocalDateTime BEGIN_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0, 0);
    static final LocalDateTime END_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0);

    private CheckInProvider checkInProvider;


    ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    Integer count() {
        Collection<CheckIn> checkIns = checkInProvider.getCheckIns();

        int nbColdMeals = 0;
        for (CheckIn checkIn : checkIns) {
            if (checkIn.isBetween(BEGIN_COLD_MEALS_DATE, END_COLD_MEALS_DATE)) {
                nbColdMeals += 1;
            }
        }

        return nbColdMeals;
    }
}
