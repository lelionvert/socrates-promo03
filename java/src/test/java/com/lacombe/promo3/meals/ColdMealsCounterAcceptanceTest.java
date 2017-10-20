package com.lacombe.promo3.meals;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.lacombe.promo3.meals.CheckInGenerator.checkInOnFirstDay;
import static com.lacombe.promo3.meals.CheckInGenerator.checkInWithoutDate;

public class ColdMealsCounterAcceptanceTest {
    private CheckIn regisCheckIn;
    private CheckIn fannyCheckIn;
    private CheckIn emilieCheckIn;
    private CheckIn julesCheckIn;
    private RegistrationBook registrationBook;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_have_zero_cold_meals_when_all_participants_are_not_late() throws Exception {
        // Arrange
        regisCheckIn = checkInOnFirstDay(16, 30, "regis.dubois@socrates.com");
        fannyCheckIn = checkInOnFirstDay(18, 30, "fanny.dubois@crafts.com");
        emilieCheckIn = checkInOnFirstDay(19, 0, "emilie.dupuy@testing.fr");
        julesCheckIn = checkInOnFirstDay(20, 45, "jules.fournier@xp.com");
        registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckIn, emilieCheckIn, julesCheckIn);

        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(registrationBook);
        ColdMealsCounter coldMealsCounter = new ColdMealsCounter(checkInProvider);

        // Act
        Integer countColdMeals = coldMealsCounter.count();

        // Assert
        Assertions.assertThat(countColdMeals).isEqualTo(0);
    }

    @Test
    public void should_have_one_or_several_cold_meals_when_one_or_several_participants_are_late() throws Exception {
        // Arrange
        regisCheckIn = checkInOnFirstDay(22, 0,"regis.dubois@socrates.com");
        fannyCheckIn = checkInOnFirstDay(17, 30, "fanny.dubois@crafts.com");
        emilieCheckIn = checkInOnFirstDay(11, 0, "emilie.dupuy@testing.fr");
        julesCheckIn = checkInOnFirstDay(21, 15, "jules.fournier@xp.com");
        registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckIn, emilieCheckIn, julesCheckIn);

        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(registrationBook);
        ColdMealsCounter coldMealsCounter = new ColdMealsCounter(checkInProvider);

        // Act
        Integer countColdMeals = coldMealsCounter.count();

        // Assert
        Assertions.assertThat(countColdMeals).isEqualTo(2);
    }

    @Test
    public void should_have_no_cold_meals_when_one_or_several_participants_do_not_give_check_in_date() throws Exception {
        // Arrange
        regisCheckIn = checkInWithoutDate("regis.dubois@socrates.com");
        fannyCheckIn = checkInWithoutDate("fanny.dubois@crafts.com");
        emilieCheckIn = checkInWithoutDate("emilie.dupuy@testing.fr");
        julesCheckIn = checkInOnFirstDay(20, 45, "jules.fournier@xp.com");
        registrationBook = RegistrationBook.of(regisCheckIn, fannyCheckIn, emilieCheckIn, julesCheckIn);

        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(registrationBook);
        ColdMealsCounter coldMealsCounter = new ColdMealsCounter(checkInProvider);

        // Act
        Integer countColdMeals = coldMealsCounter.count();

        // Assert
        Assertions.assertThat(countColdMeals).isEqualTo(0);
    }
}
