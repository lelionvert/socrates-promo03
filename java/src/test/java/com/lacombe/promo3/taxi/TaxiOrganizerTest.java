package com.lacombe.promo3.taxi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TaxiOrganizerTest {

    @Test
    public void should_book_one_taxi_for_one_arrival_on_12h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(12);
        Arrival arrival = new Arrival(arrivalHour, participant);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrival).getBookingsWithoutProvider();

        //THEN
        assertThat(booking.getTaxi().toString()).isEqualTo("Taxi_12h");
        assertThat(booking.getHoraire()).isEqualTo(LocalDateTime.of(2017, Month.OCTOBER, 26, 12, 0));
        assertThat(booking.getPassengerName()).isEqualTo("Thierry de Pauw");
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), "12", "Thierry de Pauw"));
    }

    @Test
    public void should_book_one_taxi_for_one_checkin_on_13h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(13);
        Arrival arrival = new Arrival(arrivalHour, participant);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrival).getBookingsWithoutProvider();

        //THEN
        assertThat(booking.getTaxi().toString()).isEqualTo("Taxi_13h");
        assertThat(booking.getHoraire()).isEqualTo(LocalDateTime.of(2017, Month.OCTOBER, 26, 13, 0));
        assertThat(booking.getPassengerName()).isEqualTo("Thierry de Pauw");
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_13h"), "13", "Thierry de Pauw"));
    }

    /*@Mock
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

    }*/
}