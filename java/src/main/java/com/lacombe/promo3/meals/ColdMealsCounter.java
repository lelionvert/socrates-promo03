package com.lacombe.promo3.meals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.stream.Collectors;

class ColdMealsCounter {
    static final LocalDateTime BEGIN_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 0, 0, 0);
    static final LocalDateTime END_COLD_MEALS_DATE = LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0);

    private final CheckInProvider checkInProvider;


    ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    Integer count() {
        Collection<CheckIn> checkIns = checkInProvider.getCheckIns();
        checkIns = checkIns.stream().filter(checkIn -> checkIn.isBetween(BEGIN_COLD_MEALS_DATE, END_COLD_MEALS_DATE)).collect(Collectors.toList());
        return checkIns.size();
    }
}