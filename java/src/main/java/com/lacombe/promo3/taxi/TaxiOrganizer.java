package com.lacombe.promo3.taxi;

import com.lacombe.promo3.meals.CheckIn;
import com.lacombe.promo3.meals.CheckInProvider;

public class TaxiOrganizer {

    private CheckInProvider checkInProvider;
    private TaxiBookingsResult bookingsWithoutProvider;

    public TaxiOrganizer(TaxiProvider inMemoryTaxiProvider, CheckInProvider checkInProvider) {

        this.checkInProvider = checkInProvider;
    }

    public TaxiOrganizer(Arrival arrival) {

    }

    public TaxiBookingsResult getBookings() {
        checkInProvider.getRegistrationBook();

        return TaxiBookingsResult.notFound();
    }

    public TaxiBooking getBookingsWithoutProvider() {
        return new TaxiBooking("", "","");
    }
}
