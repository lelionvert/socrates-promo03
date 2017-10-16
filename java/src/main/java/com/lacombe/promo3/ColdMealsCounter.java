package com.lacombe.promo3;

public class ColdMealsCounter {
    private CheckInProvider checkInProvider;

    public ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    public Integer count() {
        return checkInProvider.countLateCheckIns();
    }
}
