package com.lacombe.promo3;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

public class ColdMealsCounter {
    private CheckInProvider checkInProvider;

    static final LocalDateTime BEGIN_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0, 0);
    static final LocalDateTime END_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0);

    public ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    public Integer count() {
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
