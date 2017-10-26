package com.lacombe.promo3.taxi;

import java.time.LocalDateTime;
import java.time.Month;

public class TaxiBooking {

    private final Taxi taxi;
    private final LocalDateTime time;
    private final Passenger passenger;

    public TaxiBooking(Taxi taxi, String time, String passengerName) {
        this.taxi = taxi;
        this.time = LocalDateTime.of(2017, Month.OCTOBER, 26, Integer.parseInt(time), 0);
        this.passenger = new Passenger(passengerName);
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public LocalDateTime getHoraire() {
        return time;
    }

    public String getPassengerName() {
        return passenger.getPassengerName();
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
