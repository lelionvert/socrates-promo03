package com.lacombe.promo3.taxi;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.PeriodBuilder;
import com.lacombe.promo3.meals.CheckInProvider;
import com.lacombe.promo3.meals.InMemoryCheckInProvider;
import com.lacombe.promo3.meals.RegistrationBook;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

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

    @Test
    public void should_book_one_regular_taxi_for_period_with_three_checkins() throws Exception {
        //GIVEN
        final TaxiProvider taxiProvider = new InMemoryTaxiProvider();
        final CheckInProvider inMemoryCheckInProvider = new InMemoryCheckInProvider(RegistrationBook.of());

        final TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, inMemoryCheckInProvider);

        //WHEN
        final TaxiBookingsResult taxiBookingsResult = taxiOrganizer.getBookings();

        //THEN
        /*Taxi taxi = Taxi.asRegular();
        Collection<TaxiBooking> taxiBookings = Arrays.asList(new TaxiBooking());
        assertThat(taxiBookingsResult).isEqualTo(TaxiBookingsResult.found());*/
    }
}
