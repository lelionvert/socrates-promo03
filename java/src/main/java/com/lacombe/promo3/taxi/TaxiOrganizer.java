package com.lacombe.promo3.taxi;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.meals.CheckInProvider;
import com.lacombe.promo3.meals.InMemoryCheckInProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TaxiOrganizer {

    public TaxiOrganizer(InMemoryTaxiProvider inMemoryTaxiProvider, CheckInProvider inMemoryCheckInProvider) {

    }

    public TaxiBookings getBookings(Period period) {
        throw new NotImplementedException();
    }
}
