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

    @Test
    public void should_book_one_taxi_for_one_arrival_on_12h() throws Exception {
        //GIVEN
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(12);
        Arrival arrival = new Arrival(arrivalHour, participant);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrival).getBookingsWithoutProvider();

        //THEN
        assertThat(booking.getTaxiName()).isEqualTo("Taxi_12h");
        assertThat(booking.getHoraire()).isEqualTo("12h");
        assertThat(booking.getPassager()).isEqualTo("Thierry de Pauw");
        assertThat(booking).isEqualTo(new TaxiBooking("Taxi_12h", "12h", "Thierry de Pauw"));
    }

    /*@Test
    public void should_book_one_taxi_for_one_checkin_on_13h() throws Exception {
        //GIVEN
        Taxi taxi = new Taxi();
        Participant participant = new Participant("Thierry de Pauw");
        ArrivalHour arrivalHour = new ArrivalHour(13);
        Arrival arrival = new Arrival(arrivalHour, participant);

        //WHEN
        TaxiBooking booking = new TaxiOrganizer(arrival, taxi).getBookingsWithoutProvider();

        //THEN
        assertThat(booking.getTaxiName()).isEqualTo("Taxi_13h");
        assertThat(booking.getHoraire()).isEqualTo("13h");
        assertThat(booking.getPassager()).isEqualTo("Thierry de Pauw");
        assertThat(booking).isEqualTo(new TaxiBooking("Taxi_13h", "13h", "Thierry de Pauw"));
    }*/
}
