package com.lacombe.promo3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ColdMealsCounter {
    private CheckInProvider checkInProvider;

    public ColdMealsCounter(CheckInProvider checkInProvider) {
        this.checkInProvider = checkInProvider;
    }

    public Integer count() {
        return checkInProvider.countLateCheckins();
    }
}
