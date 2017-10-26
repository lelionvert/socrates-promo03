package com.lacombe.promo3.taxi;

public class Passenger {
    final String passengerName;

    public Passenger(String passengerName) {
        this.passengerName = passengerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        return passengerName != null ? passengerName.equals(passenger.passengerName) : passenger.passengerName == null;
    }

    @Override
    public int hashCode() {
        return passengerName != null ? passengerName.hashCode() : 0;
    }
}