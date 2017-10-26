package com.lacombe.promo3.taxi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Arrivals {

    private Collection<Arrival> arrivals = new ArrayList<>();

    public ArrivalHour getLastArrivalTime() {
       return arrivals.stream().map( a -> a.getHour()).max(Comparator.naturalOrder()).orElse(null);
    }

    public void add(Arrival arrival) {
        arrivals.add(arrival);
    }

    public Collection<Arrival> getArrivals() {
        return Collections.unmodifiableCollection(arrivals);
    }
}
