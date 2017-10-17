package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class CheckInTest {

    private CheckIn regisCheckIn;

    @Before
    public void setUp() throws Exception {
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"));
    }

    @Test
    public void should_not_be_late_when_check_in_date_is_not_given() throws Exception {
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isFalse();
    }

    @Test
    public void should_not_be_late_when_check_in_date_is_after_thursday_night() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 28, 0, 0, 0, 0));
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isFalse();
    }

    @Test
    public void should_be_late_when_check_in_date_is_thursday_night() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 1, 0, 1));
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isTrue();
    }

    @Test
    public void should_not_be_late_when_check_in_date_is_before_the_begin_of_cold_meals() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 27, 20, 1, 0, 1));
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isFalse();
    }
}
