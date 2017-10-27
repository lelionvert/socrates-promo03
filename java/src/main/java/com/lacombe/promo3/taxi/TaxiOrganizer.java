package com.lacombe.promo3.taxi;

import java.time.LocalTime;

public class TaxiOrganizer {

    private final Arrivals arrivals;

    public TaxiOrganizer(Arrivals arrivals) {
        this.arrivals = arrivals;
    }

    public TaxiBooking getBookings() {

        final ArrivalHour lastArrivalTime = arrivals.getLastArrivalTime();

        Taxi taxi = new Taxi("Taxi_"+ lastArrivalTime +"h");
        LocalTime departureTime = lastArrivalTime.getArrivalTime();
        Passengers passengers = new Passengers();

        for (Arrival arrival : arrivals.getArrivals()) {
            passengers.add(new Passenger(arrival.getParticipantName()));
        }

        return new TaxiBooking(taxi, departureTime, passengers);
    }

}
