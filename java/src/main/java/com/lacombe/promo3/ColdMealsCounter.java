package com.lacombe.promo3;

import java.util.Collection;

public class ColdMealsCounter {
    private CheckInProvider checkInProvider;

    public ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    public Integer count() {
        Collection<CheckIn> checkIns = checkInProvider.getCheckIns();

        int nbColdMeals = 0;
        for (CheckIn checkIn : checkIns) {
            if (checkIn.isLate()) {
                nbColdMeals += 1;
            }
        }

        return nbColdMeals;
    }
}
