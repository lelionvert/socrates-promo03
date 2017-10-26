package com.lacombe.promo3.taxi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TaxiOrganizerTest {

    @Test
    public void should_book_one_taxi_for_one_arrival_on_12h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(12);
        Arrivals arrivals = new Arrivals();
        arrivals.add(new Arrival(arrivalHour, participant));

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookings();

        //THEN
        Collection<Passenger> expectedPassengers = new ArrayList<Passenger>();
        expectedPassengers.add(new Passenger("Thierry de Pauw"));
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), "12", expectedPassengers));
    }

    @Test
    public void should_book_one_taxi_for_one_arrival_on_13h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(13);
        Arrivals arrivals = new Arrivals();
        arrivals.add(new Arrival(arrivalHour, participant));

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookings();

        //THEN
        Collection<Passenger> expectedPassengers = new ArrayList<Passenger>();
        expectedPassengers.add(new Passenger("Thierry de Pauw"));
        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_13h"), "13", expectedPassengers));
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
        TaxiBooking booking = new TaxiOrganizer(arrivals).getBookings();

        //THEN
        List<Passenger> passengersExpected = Arrays.asList(
                new Passenger("Thierry de Pauw"),
                new Passenger("Houssam"));

        assertThat(booking).isEqualTo(new TaxiBooking(new Taxi("Taxi_15h"), "15", passengersExpected));
    }
}