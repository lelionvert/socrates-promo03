package com.lacombe.promo3.meals;

import com.lacombe.promo3.registration.model.Email;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckInTest {
    private CheckIn regisCheckIn;

    @Test
    public void should_not_be_between_when_check_in_date_is_not_given() throws Exception {
        // Arrange
        regisCheckIn = MealsUtils.checkInWithoutDate("regis.dubois@socrates.com");

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_before_the_given_begin_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), ColdMealsCounter.BEGIN_COLD_MEALS_DATE.minusHours(1));

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_after_the_given_end_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), ColdMealsCounter.END_COLD_MEALS_DATE.plusHours(1));

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_exactly_the_given_begin_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), ColdMealsCounter.BEGIN_COLD_MEALS_DATE);

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_not_be_between_when_check_in_date_is_exactly_the_given_end_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), ColdMealsCounter.END_COLD_MEALS_DATE);

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isFalse();
    }

    @Test
    public void should_be_between_when_check_in_date_is_between_the_given_dates() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), ColdMealsCounter.BEGIN_COLD_MEALS_DATE.plusMinutes(1));

        // Act
        Boolean checkInBetween = regisCheckIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);

        // Assert
        assertThat(checkInBetween).isTrue();
    }
}
