package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

public class ArrivalHour implements Comparable<ArrivalHour>{
    private final int hour;
    private final int minute;

    public ArrivalHour(int hour) {
        this(hour, 0);
    }

    public ArrivalHour(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public LocalTime generateLocalTime() {
        return LocalTime.of(hour,minute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalHour that = (ArrivalHour) o;

        if (hour != that.hour) return false;
        return minute == that.minute;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(hour);
    }

    @Override
    public int compareTo(ArrivalHour o) {
        return this.hour - o.hour;
    }
}
