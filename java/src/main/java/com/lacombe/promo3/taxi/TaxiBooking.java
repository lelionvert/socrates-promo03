package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

public class TaxiBooking {

    private final Taxi taxi;
    private final LocalDateTime time;
    private final Passenger passenger;
    private final Collection<Passenger> passengers;

    public TaxiBooking(Taxi taxi, String time, String passengerName) {
        this.taxi = taxi;
        this.time = LocalDateTime.of(2017, Month.OCTOBER, 26, Integer.parseInt(time), 0);
        this.passenger = new Passenger(passengerName);
        passengers = new ArrayList<>();
        passengers.add(passenger);
    }

    public TaxiBooking(Taxi taxi, String time, Collection<Passenger> passengers) {
        this.taxi = taxi;
        this.time = LocalDateTime.of(2017, Month.OCTOBER, 26, Integer.parseInt(time), 0);
        this.passengers = passengers;
        this.passenger = passengers.iterator().next();
    }

    public Taxi getTaxi() {
        return taxi;
    }

    @Override
    public int hashCode() {
        int result = taxi != null ? taxi.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiBooking that = (TaxiBooking) o;

        if (taxi != null ? !taxi.equals(that.taxi) : that.taxi != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return passenger != null ? passenger.equals(that.passenger) : that.passenger == null;
    }

}
