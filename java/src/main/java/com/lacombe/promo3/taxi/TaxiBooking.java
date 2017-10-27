package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

public class TaxiBooking {

    private final Taxi taxi;
    private final LocalDateTime time;
    private final Collection<Passenger> passengers;

    public TaxiBooking(Taxi taxi, String time, Collection<Passenger> passengers) {
        this.taxi = taxi;
        this.time = LocalDateTime.of(2017, Month.OCTOBER, 26, Integer.parseInt(time), 0);
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiBooking that = (TaxiBooking) o;

        if (taxi != null ? !taxi.equals(that.taxi) : that.taxi != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return passengers != null ? passengers.equals(that.passengers) : that.passengers == null;
    }

    @Override
    public int hashCode() {
        int result = taxi != null ? taxi.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        return result;
    }

    public Taxi getTaxi() {
        return taxi;
    }
}
