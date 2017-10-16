package com.lacombe.promo3;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ColdMealsCounterTest {
    @Test
    public void should_call_method_countLateCheckins_when_count_cold_meals() throws Exception {
        //Arrange
        CheckInProvider checkInProvider = mock(CheckInProvider.class);
        ColdMealsCounter counter = new ColdMealsCounter(checkInProvider);
        when(checkInProvider.countLateCheckins()).thenReturn(0);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        Assertions.assertThat(nbColdMeals).isEqualTo(0);
        verify(checkInProvider).countLateCheckins();
    }

    @Test
    public void should_have_one_cold_meals_when_there_is_one_late_check_in() throws Exception {
        //Arrange
        CheckInProvider checkInProvider = mock(CheckInProvider.class);
        ColdMealsCounter counter = new ColdMealsCounter(checkInProvider);
        when(checkInProvider.countLateCheckins()).thenReturn(1);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        Assertions.assertThat(nbColdMeals).isEqualTo(1);
    }
}