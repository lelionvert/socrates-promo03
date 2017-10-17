
package com.lacombe.promo3;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class ColdMealsCounterTest {


    @Test
    public void should_call_method_countLateCheckIns_when_count_cold_meals() throws Exception {
        //Arrange
        CheckInProvider checkInProvider = mock(CheckInProvider.class);
        ColdMealsCounter counter = new ColdMealsCounter(checkInProvider);
        when(checkInProvider.getCheckIns()).thenReturn(Collections.emptyList());

        //Act
        int nbColdMeals = counter.count();

        //Assert
        Assertions.assertThat(nbColdMeals).isEqualTo(0);
        verify(checkInProvider).getCheckIns();
    }

    @Test
    public void should_have_one_cold_meals_when_there_is_one_late_check_in() throws Exception {
        //Arrange
        CheckInProvider checkInProvider = mock(CheckInProvider.class);
        CheckIn checkIn = mock(CheckIn.class);
        ColdMealsCounter counter = new ColdMealsCounter(checkInProvider);
        when(checkIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE,
                ColdMealsCounter.END_COLD_MEALS_DATE)).thenReturn(true);
        when(checkInProvider.getCheckIns()).thenReturn(Collections.singletonList(checkIn));

        //Act
        int nbColdMeals = counter.count();

        //Assert
        verify(checkIn).isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        Assertions.assertThat(nbColdMeals).isEqualTo(1);
    }
}