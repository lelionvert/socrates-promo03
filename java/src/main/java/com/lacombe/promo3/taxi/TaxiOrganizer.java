package com.lacombe.promo3.taxi;

import com.lacombe.promo3.meals.CheckInProvider;

import java.util.ArrayList;
import java.util.Collection;

public class TaxiOrganizer {

    private final Arrival arrival;
    private final Arrivals arrivals;
    private CheckInProvider checkInProvider;
    private TaxiBookingsResult bookingsWithoutProvider;

    public TaxiOrganizer(TaxiProvider inMemoryTaxiProvider, CheckInProvider checkInProvider) {

        this.checkInProvider = checkInProvider;
        arrival = null;
        arrivals = null;
    }

    public TaxiOrganizer(Arrival arrival) {

        this.arrival = arrival;
        this.arrivals = new Arrivals();
        arrivals.add(arrival);
    }

    public TaxiOrganizer(Arrivals arrivals) {
        this.arrivals = arrivals;
        arrival = arrivals.getArrivals().iterator().next();
    }

    public TaxiBookingsResult getBookings() {
        checkInProvider.getRegistrationBook();

        return TaxiBookingsResult.notFound();
    }

    public TaxiBooking getBookingsWithoutProvider() {
        Taxi taxi = new Taxi("Taxi_"+arrival.getHour()+"h");
        String time = String.valueOf(arrival.getHour());
        Collection<Passenger> passengers = new ArrayList<>();

        for (Arrival arrival : arrivals.getArrivals()) {
            passengers.add(new Passenger(arrival.getParticipantName()));
        }

        return new TaxiBooking(taxi, time, passengers);
    }
}
