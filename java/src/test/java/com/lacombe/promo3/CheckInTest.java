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
        regisCheckIn = new CheckIn(Email.of("regis.dubois@socrates.com"));
    }

    @Test
    public void should_not_be_late_when_check_in_date_is_not_given() throws Exception {
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isFalse();
    }

    @Test
    public void should_not_be_late_when_check_in_date_is_after_thursday() throws Exception {
        regisCheckIn.setCheckingDate(LocalDateTime.of(2017, Month.OCTOBER,28,0,0,0,0));
        Boolean isLate = regisCheckIn.isLate();
        Assertions.assertThat(isLate).isFalse();
    }
}
