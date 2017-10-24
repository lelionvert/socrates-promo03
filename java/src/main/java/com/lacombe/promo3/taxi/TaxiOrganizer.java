package com.lacombe.promo3.taxi;

import com.lacombe.promo3.meals.CheckInProvider;

public class TaxiOrganizer {

    private CheckInProvider checkInProvider;

    public TaxiOrganizer(TaxiProvider inMemoryTaxiProvider, CheckInProvider checkInProvider) {

        this.checkInProvider = checkInProvider;
    }

    public TaxiBookingsResult getBookings() {
        checkInProvider.getRegistrationBook();

        return TaxiBookingsResult.notFound();
    }
}
