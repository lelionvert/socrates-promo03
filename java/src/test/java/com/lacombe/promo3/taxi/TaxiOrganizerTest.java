package com.lacombe.promo3.taxi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxiOrganizerTest {

    @Test
    public void should_book_one_taxi_for_one_arrival_on_12h() throws Exception {
        //GIVEN
        Arrivals arrivals = new Arrivals();
        arrivals.add(new Arrival(new ArrivalHour(12), new Participant("Thierry de Pauw")));

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookings();

        //THEN
        Collection<Passenger> expectedPassengers = new ArrayList<Passenger>();
        expectedPassengers.add(new Passenger("Thierry de Pauw"));
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), "12", expectedPassengers));
    }

    @Test
    public void should_book_one_taxi_for_two_arrival_on_15h() throws Exception {
        //GIVEN
        ArrivalHour arrivalHour = new ArrivalHour(15);
        Arrivals arrivals = new Arrivals();
        arrivals.add(new Arrival(arrivalHour, new Participant("Thierry de Pauw")));
        arrivals.add(new Arrival(arrivalHour, new Participant("Houssam")));

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookings();

        //THEN
        List<Passenger> passengersExpected = Arrays.asList(
                new Passenger("Thierry de Pauw"),
                new Passenger("Houssam"));
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_15h"), "15", passengersExpected));
    }

    @Test
    public void should_book_one_taxi_for_three_arrivals_between_12h_and_12h30() throws Exception {
        //GIVEN
        Arrivals arrivals = new Arrivals();
        arrivals.add(new Arrival(new ArrivalHour(12), new Participant("Thierry de Pauw")));
        arrivals.add(new Arrival(new ArrivalHour(12, 15), new Participant("Houssam")));
        arrivals.add(new Arrival(new ArrivalHour(12, 30), new Participant("Arnaud Lemaire")));

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookingsBis();

        //THEN
        List<Passenger> passengersExpected = Arrays.asList(
            new Passenger("Thierry de Pauw"),
            new Passenger("Houssam"),
            new Passenger("Arnaud Lemaire"));
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), LocalTime.of(12, 30), passengersExpected));
    }
}