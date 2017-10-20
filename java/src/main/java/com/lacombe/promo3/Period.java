package com.lacombe.promo3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;

public class Period {

    public Period(LocalDateTime begin, LocalDateTime end) {
    }

    public Boolean include(LocalDateTime dateTime) {
        return true;
    }
}
