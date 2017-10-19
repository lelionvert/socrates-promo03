package com.lacombe.promo3.meals;

import java.time.LocalDateTime;
import java.time.Month;

class ColdMealsCounter {
    static final LocalDateTime BEGIN_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0, 0);
    static final LocalDateTime END_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0);

    private final CheckInProvider checkInProvider;

    ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    int count() {
        CheckIns checkIns = checkInProvider.getCheckIns();
        return (checkIns == null) ? 0: checkIns.countBetween(BEGIN_COLD_MEALS_DATE, END_COLD_MEALS_DATE);
    }
}