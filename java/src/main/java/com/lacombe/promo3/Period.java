package com.lacombe.promo3;

import java.time.LocalDateTime;

public class Period {

    private final LocalDateTime begin;
    private final LocalDateTime end;

    public Period(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public Boolean include(LocalDateTime dateTime) {
        return dateTime != null
                && dateTime.isAfter(begin)
                && dateTime.isBefore(end);
    }
}
