package com.lacombe.promo3.taxi;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.meals.CheckInProvider;
import com.lacombe.promo3.meals.InMemoryCheckInProvider;
import org.junit.runner.RunWith;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TaxiOrganizer {

    private CheckInProvider checkInProvider;

    public TaxiOrganizer(TaxiProvider inMemoryTaxiProvider, CheckInProvider checkInProvider) {

        this.checkInProvider = checkInProvider;
    }

    public TaxiBookings getBookings(Period period) {
        checkInProvider.getRegistrationBook();

        return null;
    }
}
