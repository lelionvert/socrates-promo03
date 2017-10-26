package com.lacombe.promo3.taxi;

import com.lacombe.promo3.meals.CheckIn;
import com.lacombe.promo3.meals.CheckInProvider;

public class TaxiOrganizer {

    private final Arrival arrival;
    private CheckInProvider checkInProvider;
    private TaxiBookingsResult bookingsWithoutProvider;

    public TaxiOrganizer(TaxiProvider inMemoryTaxiProvider, CheckInProvider checkInProvider) {

        this.checkInProvider = checkInProvider;
        arrival = null;
    }

    public TaxiOrganizer(Arrival arrival) {

        this.arrival = arrival;
    }

    public TaxiBookingsResult getBookings() {
        checkInProvider.getRegistrationBook();

        return TaxiBookingsResult.notFound();
    }

    public TaxiBooking getBookingsWithoutProvider() {
        Taxi taxi = new Taxi("Taxi_"+arrival.getHour()+"h");
        String time = String.valueOf(arrival.getHour());
        String passengerName = arrival.getPassengerName();

        return new TaxiBooking(taxi, time, passengerName);
    }
}
