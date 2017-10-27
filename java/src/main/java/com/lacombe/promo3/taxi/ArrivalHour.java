package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

public class ArrivalHour implements Comparable<ArrivalHour>{

    private LocalTime arrivalTime;

    public ArrivalHour(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalHour that = (ArrivalHour) o;

        return arrivalTime != null ? arrivalTime.equals(that.arrivalTime) : that.arrivalTime == null;
    }

    @Override
    public int hashCode() {
        return arrivalTime != null ? arrivalTime.hashCode() : 0;
    }

    @Override
    public String toString() {
        return Integer.toString(arrivalTime.getHour());
    }

    @Override
    public int compareTo(ArrivalHour o) {
        return this.arrivalTime.compareTo(o.arrivalTime);
    }
}
