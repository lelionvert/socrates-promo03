package com.lacombe.promo3.taxi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

public class TaxiOrganizer {

    private final Arrivals arrivals;

    public TaxiOrganizer(Arrivals arrivals) {
        this.arrivals = arrivals;
    }

    public TaxiBooking getBookings() {

        final ArrivalHour lastArrivalTime = arrivals.getLastArrivalTime();

        Taxi taxi = new Taxi("Taxi_"+ lastArrivalTime +"h");
        String time = String.valueOf(lastArrivalTime);
        Collection<Passenger> passengers = new ArrayList<>();

        for (Arrival arrival : arrivals.getArrivals()) {
            passengers.add(new Passenger(arrival.getParticipantName()));
        }

        return new TaxiBooking(taxi, time, passengers);
    }

    public TaxiBooking getBookingsBis() {

        final ArrivalHour lastArrivalTime = arrivals.getLastArrivalTime();

        Taxi taxi = new Taxi("Taxi_"+ lastArrivalTime +"h");
        LocalTime departureTime = lastArrivalTime.generateLocalTime();
        Collection<Passenger> passengers = new ArrayList<>();

        for (Arrival arrival : arrivals.getArrivals()) {
            passengers.add(new Passenger(arrival.getParticipantName()));
        }

        return new TaxiBooking(taxi, departureTime, passengers);
    }

}
