package com.lacombe.promo3.taxi;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.PeriodBuilder;
import com.lacombe.promo3.meals.CheckInProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaxiOrganizerTest {

    @Mock
    CheckInProvider checkInProvider;

    @Mock
    TaxiProvider taxiProvider;

    @Test
    public void getBookings_should_call_getRegistrationBook_once() throws Exception {
        // GIVEN
        final TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, checkInProvider);
        final Period bookingPeriod = PeriodBuilder
            .from(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 00))
            .to(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 30));

        // WHEN
        taxiOrganizer.getBookings();

        // THEN
        verify(checkInProvider, times(1)).getRegistrationBook();
    }

    @Test
    public void getBookings_should_not_found_bookings_when_no_checkin() throws Exception {
        final Period bookingPeriod = PeriodBuilder
            .from(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 00))
            .to(LocalDateTime.of(2017, Month.OCTOBER, 27, 12, 30));

        final TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, checkInProvider);

        //WHEN
        final TaxiBookingsResult taxiBookingsResult = taxiOrganizer.getBookings();

        //THEN
        assertThat(taxiBookingsResult).isEqualTo(TaxiBookingsResult.notFound());

    }
}