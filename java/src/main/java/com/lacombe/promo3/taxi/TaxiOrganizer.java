package com.lacombe.promo3.taxi;

import java.util.ArrayList;
import java.util.Collection;

public class TaxiOrganizer {

    private final Arrivals arrivals;

    public TaxiOrganizer(Arrivals arrivals) {
        this.arrivals = arrivals;
    }

    public TaxiBooking getBookings() {
        Arrival firstArrival = arrivals.getArrivals().iterator().next();

        Taxi taxi = new Taxi("Taxi_"+firstArrival.getHour()+"h");
        String time = String.valueOf(firstArrival.getHour());
        Collection<Passenger> passengers = new ArrayList<>();

        for (Arrival arrival : arrivals.getArrivals()) {
            passengers.add(new Passenger(arrival.getParticipantName()));
        }

        return new TaxiBooking(taxi, time, passengers);
    }
}
