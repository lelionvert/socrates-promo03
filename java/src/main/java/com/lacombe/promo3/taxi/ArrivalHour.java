package com.lacombe.promo3.taxi;

public class ArrivalHour {
    private final int hour;

    public ArrivalHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalHour that = (ArrivalHour) o;

        return hour == that.hour;
    }

    @Override
    public int hashCode() {
        return hour;
    }

    @Override
    public String toString() {
        return Integer.toString(hour);
    }
}
