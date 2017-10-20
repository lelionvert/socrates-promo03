
package com.lacombe.promo3.meals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.lacombe.promo3.meals.ColdMealsCounter.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ColdMealsCounterTest {


    @Mock
    private CheckInProvider checkInProvider;
    @Mock
    private CheckIn checkIn;

    private ColdMealsCounter counter;

    @Mock
    private RegistrationBook registrationBook;

    @Before
    public void setUp() throws Exception {
        counter = new ColdMealsCounter(checkInProvider);
    }

    @Test
    public void should_have_zero_cold_meal_when_there_is_no_check_in() throws Exception {
        //Arrange
        when(checkInProvider.getRegistrationBook()).thenReturn(registrationBook);
        when(registrationBook.isEmpty()).thenReturn(false);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        verify(checkInProvider).getRegistrationBook();
        assertThat(nbColdMeals).isEqualTo(0);
    }

    @Test
    public void should_have_one_cold_meal_when_there_is_one_late_check_in() throws Exception {
        //Arrange
        registrationBook = RegistrationBook.of(checkIn);
        when(checkIn.isBetween(BEGIN_DATE, END_DATE)).thenReturn(true);
        when(checkInProvider.getRegistrationBook()).thenReturn(registrationBook);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        verify(checkIn).isBetween(BEGIN_DATE, END_DATE);
        assertThat(nbColdMeals).isEqualTo(1);
    }

    @Test
    public void should_have_zero_cold_meal_when_check_in_date_is_before_begin_cold_meals_date() throws Exception {
        //Arrange
        registrationBook = RegistrationBook.of(checkIn);
        when(checkIn.isBetween(BEGIN_DATE, END_DATE)).thenReturn(false);
        when(checkInProvider.getRegistrationBook()).thenReturn(registrationBook);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        assertThat(nbColdMeals).isEqualTo(0);
    }
}