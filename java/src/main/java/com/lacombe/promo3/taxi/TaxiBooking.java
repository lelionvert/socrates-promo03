package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;

public class TaxiBooking {

    private final Taxi taxi;
    private final LocalTime departureTime;
    private final Passengers passengers;

    public TaxiBooking(Taxi taxi, LocalTime departureTime, Passengers passengers) {
        this.taxi = taxi;
        this.departureTime = departureTime;
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiBooking that = (TaxiBooking) o;

        if (taxi != null ? !taxi.equals(that.taxi) : that.taxi != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        return passengers != null ? passengers.equals(that.passengers) : that.passengers == null;
    }

    @Override
    public int hashCode() {
        int result = taxi != null ? taxi.hashCode() : 0;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        return result;
    }
}
