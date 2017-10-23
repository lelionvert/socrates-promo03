package com.lacombe.promo3;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(begin, period.begin) &&
                Objects.equals(end, period.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }
}
