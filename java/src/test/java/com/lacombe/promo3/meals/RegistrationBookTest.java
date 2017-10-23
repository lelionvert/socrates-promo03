package com.lacombe.promo3.meals;

import com.lacombe.promo3.PeriodBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.lacombe.promo3.meals.ColdMealsCounter.BEGIN_DATE;
import static com.lacombe.promo3.meals.ColdMealsCounter.END_DATE;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationBookTest {


    @Test
    public void should_return_one_when_we_have_one_late_checkins() throws Exception {
        // Arrange
        CheckIn regisCheckIn = CheckInGenerator.checkInOnFirstDay(22,10, "regis@gmail.fr");
        CheckIn fannyCheckin = CheckInGenerator.checkInWithoutDate("fanny@yahoo.fr");
        RegistrationBook registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckin);
        //Act
        int nbCheckinsIncludedIn = registrationBook.countIncludedIn(PeriodBuilder.from(BEGIN_DATE).to(END_DATE));
        //Assert
        Assertions.assertThat(nbCheckinsIncludedIn).isEqualTo(1);
    }

    @Test
    public void should_return_total_checkins_number() throws Exception {
        // Arrange
        CheckIn regisCheckIn = CheckInGenerator.checkInOnFirstDay(22,10, "regis@gmail.fr");
        CheckIn fannyCheckin = CheckInGenerator.checkInWithoutDate("fanny@yahoo.fr");
        RegistrationBook registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckin);
        //Act
        int nbCheckIns = registrationBook.size();
        //Assert
        Assertions.assertThat(nbCheckIns).isEqualTo(2);
    }

    @Test
    public void should_be_empty_if_the_registration_book_got_no_chekins() throws Exception {
        RegistrationBook registrationBook = RegistrationBook.of(new CheckIn[0]);
        //Act
        Boolean isEmpty = registrationBook.isEmpty();
        //Assert
        Assertions.assertThat(isEmpty).isTrue();
    }

    @Test
    public void should_the_email_exists_in_the_registration_book() throws Exception {
        //Arrange
        CheckIn regisCheckIn = CheckInGenerator.checkInOnFirstDay(22,10, "regis@gmail.fr");
        CheckIn fannyCheckin = CheckInGenerator.checkInWithoutDate("fanny@yahoo.fr");
        RegistrationBook registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckin);

        //Act
        CheckIn existingCheckIn = registrationBook.getCheckInForEmail(CheckInGenerator.checkInOnFirstDay(22,10, "fanny@yahoo.fr"));

        //Assert
        Assertions.assertThat(existingCheckIn).isNotNull();

    }

    @Test
    public void should_the_email_not_exists_in_the_registration_book() throws Exception {
        //Arrange
        CheckIn regisCheckIn = CheckInGenerator.checkInOnFirstDay(22,10, "regis@gmail.fr");
        CheckIn fannyCheckin = CheckInGenerator.checkInWithoutDate("fanny@yahoo.fr");
        RegistrationBook registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckin);

        //Act
        CheckIn existingCheckIn = registrationBook.getCheckInForEmail(CheckInGenerator.checkInOnFirstDay(22,10, "fannydubois@yahoo.fr"));

        //Assert
        Assertions.assertThat(existingCheckIn).isNull();

    }

    @Test
    public void should_add_the_checkin_if_a_checkin_with_the_same_email_already_exists() throws Exception {
        //Arrange
        CheckIn regisCheckIn = CheckInGenerator.checkInOnFirstDay(22,10, "regis@gmail.fr");
        CheckIn fannyCheckin = CheckInGenerator.checkInWithoutDate("fanny@yahoo.fr");
        RegistrationBook registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckin);

        CheckIn checkInToAdd = CheckInGenerator.checkInOnFirstDay(22,10, "fanny@yahoo.fr");
        // Act
        registrationBook.register(checkInToAdd);

        //Assert
        Assertions.assertThat(registrationBook.contains(checkInToAdd)).isTrue();
        Assertions.assertThat(registrationBook.contains(fannyCheckin)).isFalse();
    }
}