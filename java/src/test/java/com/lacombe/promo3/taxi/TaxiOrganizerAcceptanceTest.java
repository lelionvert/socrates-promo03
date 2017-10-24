package com.lacombe.promo3.taxi;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.PeriodBuilder;
import com.lacombe.promo3.meals.CheckInProvider;
import com.lacombe.promo3.meals.InMemoryCheckInProvider;
import com.lacombe.promo3.meals.RegistrationBook;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class TaxiOrganizerAcceptanceTest {

    @Test
    public void should_have_no_taxi_for_no_checkin() throws Exception {
        //GIVEN
        final Period bookingPeriod = PeriodBuilder
            .from(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 00))
            .to(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 30));
        final InMemoryTaxiProvider inMemoryTaxiProvider = new InMemoryTaxiProvider();
        final CheckInProvider inMemoryCheckInProvider = new InMemoryCheckInProvider(RegistrationBook.of());

        final TaxiOrganizer taxiOrganizer = new TaxiOrganizer(inMemoryTaxiProvider, inMemoryCheckInProvider);

        //WHEN
        final TaxiBookings bookings = taxiOrganizer.getBookings(bookingPeriod);

        //THEN
        assertThat(bookings).isEqualTo(new TaxiBookings());
    }
}
