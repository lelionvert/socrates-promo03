package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckInTest {

    private CheckIn regisCheckIn;

    @Before
    public void setUp() throws Exception {
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"));
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_not_given() throws Exception {
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_before_the_given_begin_date() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 27, 20, 1, 0, 1));
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_after_the_given_end_date() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 28, 1, 0, 0, 0));
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_exactly_the_given_begin_date() throws Exception {
        regisCheckIn.setCheckInDate(ColdMealsCounter.BEGIN_COLD_MEALS_DATE);
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_exactly_the_given_end_date() throws Exception {
        regisCheckIn.setCheckInDate(ColdMealsCounter.END_COLD_MEALS_DATE);
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_be_between_when_check_in_date_is_between_the_given_dates() throws Exception {
        regisCheckIn.setCheckInDate(LocalDateTime.of(2017, Month.OCTOBER, 27, 21, 1, 0, 1));
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isTrue();
    }
}
