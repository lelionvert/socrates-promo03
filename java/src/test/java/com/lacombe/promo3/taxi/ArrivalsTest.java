package com.lacombe.promo3.taxi;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ArrivalsTest {

    @Test
    public void getLast_should_return_last_arrival_time_when_two_same_arrivals_hour() throws Exception {
        // GIVEN
        Participant participant1 = new Participant("Houssam");
        Participant participant2 = new Participant("Thierry de Pauw");

        ArrivalHour arrivalHour = new ArrivalHour(15);

        Arrival arrival = new Arrival(arrivalHour, participant1);
        Arrival arrival2 = new Arrival(arrivalHour, participant2);
        Arrivals arrivals = new Arrivals();
        arrivals.add(arrival);
        arrivals.add(arrival2);

        // WHEN
        final ArrivalHour lastArrival = arrivals.getLastArrivalTime();

        // THEN
        Assertions.assertThat(lastArrival).isEqualTo(new ArrivalHour(15));
    }
    @Test
    public void getLast_should_return_last_arrival_time_when_two_differents_arrivals_hour() throws Exception {
        // GIVEN
        Participant participant1 = new Participant("Houssam");
        Participant participant2 = new Participant("Thierry de Pauw");

        ArrivalHour arrivalHour = new ArrivalHour(15);
        ArrivalHour arrivalHour2 = new ArrivalHour(16);


        Arrival arrival = new Arrival(arrivalHour, participant1);
        Arrival arrival2 = new Arrival(arrivalHour2, participant2);
        Arrivals arrivals = new Arrivals();
        arrivals.add(arrival2);
        arrivals.add(arrival);

        // WHEN
        final ArrivalHour lastArrival = arrivals.getLastArrivalTime();

        // THEN
        Assertions.assertThat(lastArrival).isEqualTo(new ArrivalHour(16));
    }

}