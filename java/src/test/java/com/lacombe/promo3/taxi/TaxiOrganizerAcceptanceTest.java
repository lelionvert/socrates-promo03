package com.lacombe.promo3.taxi;

import com.lacombe.promo3.meals.CheckIn;
import com.lacombe.promo3.meals.CheckInProvider;
import com.lacombe.promo3.meals.InMemoryCheckInProvider;
import com.lacombe.promo3.meals.RegistrationBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxiOrganizerAcceptanceTest {

    @Test
    public void should_have_no_taxi_without_checkin() throws Exception {
        //GIVEN
        final TaxiProvider taxiProvider = new InMemoryTaxiProvider();
        final CheckInProvider inMemoryCheckInProvider = new InMemoryCheckInProvider(RegistrationBook.of());

        final TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, inMemoryCheckInProvider);

        //WHEN
        final TaxiBookingsResult taxiBookingsResult = taxiOrganizer.getBookings();

        //THEN
        assertThat(taxiBookingsResult).isEqualTo(TaxiBookingsResult.notFound());
    }
}
