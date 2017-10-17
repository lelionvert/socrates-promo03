package com.lacombe.promo3.meals;

import com.lacombe.promo3.registration.model.Email;
import org.junit.Before;
import org.junit.Test;

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
        regisCheckIn.setCheckInDate(ColdMealsCounter.BEGIN_COLD_MEALS_DATE.minusHours(1));
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_after_the_given_end_date() throws Exception {
        regisCheckIn.setCheckInDate(ColdMealsCounter.END_COLD_MEALS_DATE.plusHours(1));
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
        regisCheckIn.setCheckInDate(ColdMealsCounter.BEGIN_COLD_MEALS_DATE.plusMinutes(1));
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(checkInBetween).isTrue();
    }
}
