package com.lacombe.promo3;

import java.time.LocalDateTime;

public class PeriodBuilder {
    private LocalDateTime begin;
    private LocalDateTime end;

    private PeriodBuilder(LocalDateTime begin) {
        this.begin = begin;
    }

    public static PeriodBuilder from(LocalDateTime begin) {
        return new PeriodBuilder(begin);
    }

    public Period to(LocalDateTime end) {
        this.end = end;
        return this.build();
    }

    private Period build() {
        return new Period(begin, end);
    }
}