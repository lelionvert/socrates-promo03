package com.lacombe.promo3.taxi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Arrivals {

    private Collection<Arrival> arrivals = new ArrayList<>();

    public ArrivalHour getLastArrivalTime() {
        return getArrivals().iterator().next().getHour();
    }

    public void add(Arrival arrival) {
        arrivals.add(arrival);
    }

    public Collection<Arrival> getArrivals() {
        return Collections.unmodifiableCollection(arrivals);
    }
}
