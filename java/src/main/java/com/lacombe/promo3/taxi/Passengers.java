package com.lacombe.promo3.taxi;

import java.util.ArrayList;
import java.util.Collection;

public class Passengers {
    private final Collection<Passenger> passengers;

    public Passengers(Collection<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Passengers() {
        passengers = new ArrayList<>();
    }

    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passengers that = (Passengers) o;

        return passengers != null ? passengers.equals(that.passengers) : that.passengers == null;
    }

    @Override
    public int hashCode() {
        return passengers != null ? passengers.hashCode() : 0;
    }
}
