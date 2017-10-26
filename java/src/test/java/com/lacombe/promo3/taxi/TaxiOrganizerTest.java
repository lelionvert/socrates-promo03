package com.lacombe.promo3.taxi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

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
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), "12", "Thierry de Pauw"));
    }

    @Test
    public void should_book_one_taxi_for_one_arrival_on_13h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(13);
        Arrival arrival = new Arrival(arrivalHour, participant);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrival).getBookingsWithoutProvider();

        //THEN
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_13h"), "13", "Thierry de Pauw"));
    }

    @Test
    public void should_book_one_taxi_for_two_arrival_on_15h() throws Exception {
        //GIVEN
        Participant participant1 = new Participant("Thierry de Pauw");
        Participant participant2 = new Participant("Houssam");

        ArrivalHour arrivalHour = new ArrivalHour(15);

        Arrival arrival = new Arrival(arrivalHour, participant1);
        Arrival arrival2 = new Arrival(arrivalHour, participant2);
        Arrivals arrivals = new Arrivals();
        arrivals.add(arrival);
        arrivals.add(arrival2);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookingsWithoutProvider();

        //THEN
        List<Passenger> passengersExpected = Arrays.asList(
                new Passenger("Thierry de Pauw"),
                new Passenger("Houssam"));

        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_15h"), "15", passengersExpected));
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