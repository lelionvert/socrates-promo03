package com.lacombe.promo3.meals;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.PeriodBuilder;
import com.lacombe.promo3.registration.model.Email;
import org.junit.Before;
import org.junit.Test;

import static com.lacombe.promo3.PeriodBuilder.*;
import static com.lacombe.promo3.meals.ColdMealsCounter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckInTest {
    private CheckIn regisCheckIn;
    private Period period;

    @Before
    public void setUp() throws Exception {
        period = from(BEGIN_DATE).to(END_DATE);
    }


    @Test
    public void should_not_be_include_in_period_when_check_in_date_is_not_given() throws Exception {
        // Arrange
        regisCheckIn = CheckInGenerator.checkInWithoutDate("regis.dubois@socrates.com");

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isFalse();
    }

    @Test
    public void should_not_be_included_in_when_check_in_date_is_before_the_given_begin_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), BEGIN_DATE.minusHours(1));

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isFalse();
    }

    @Test
    public void should_not_be_included_in_when_check_in_date_is_after_the_given_end_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), END_DATE.plusHours(1));

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isFalse();
    }

    @Test
    public void should_not_be_included_in_when_check_in_date_is_exactly_the_given_begin_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), BEGIN_DATE);

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isFalse();
    }

    @Test
    public void should_not_be_included_in_when_check_in_date_is_exactly_the_given_end_date() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), END_DATE);

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isFalse();
    }

    @Test
    public void should_be_included_in_when_check_in_date_is_between_the_given_dates() throws Exception {
        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), BEGIN_DATE.plusMinutes(1));

        // Act
        Boolean includedIn = regisCheckIn.isIncludedIn(period);

        // Assert
        assertThat(includedIn).isTrue();
    }

    @Test
    public void should_have_the_same_email_when_email_checkin_is_equal() throws Exception {

        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), END_DATE);

        //Act
        Boolean hasTheSameEmail = regisCheckIn.hasSameEmail(CheckIn.of(Email.of("regis.dubois@socrates.com"), null));
        //Assert
        assertThat(hasTheSameEmail).isEqualTo(true);
    }

    @Test
    public void should_not_have_the_same_email_when_email_checkin_is_not_equal() throws Exception {

        // Arrange
        regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"), END_DATE);

        //Act
        Boolean hasTheSameEmail = regisCheckIn.hasSameEmail(CheckIn.of(Email.of("regis.dubois@socrates.fr"), null));
        //Assert
        assertThat(hasTheSameEmail).isEqualTo(false);
    }
}
