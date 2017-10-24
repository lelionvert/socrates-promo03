package com.lacombe.promo3.taxi;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaxiBookingsTest {

    @Test
    public void should_2_empty_taxibookings_are_equals() throws Exception {

        Assertions.assertThat(new TaxiBookings()).isEqualTo(new TaxiBookings());
    }
}