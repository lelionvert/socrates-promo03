
package com.lacombe.promo3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

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
    private List<CheckIn> checkIns;

    @Before
    public void setUp() throws Exception {
        counter = new ColdMealsCounter(checkInProvider);
        checkIns = Collections.singletonList(checkIn);

    }

    @Test
    public void should_have_zero_cold_meal_when_there_is_no_check_in() throws Exception {
        //Arrange
        when(checkInProvider.getCheckIns()).thenReturn(Collections.emptyList());

        //Act
        int nbColdMeals = counter.count();

        //Assert
        verify(checkInProvider).getCheckIns();
        assertThat(nbColdMeals).isEqualTo(0);
    }

    @Test
    public void should_have_one_cold_meal_when_there_is_one_late_check_in() throws Exception {
        //Arrange
        when(checkIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE,
                ColdMealsCounter.END_COLD_MEALS_DATE)).thenReturn(true);
        when(checkInProvider.getCheckIns()).thenReturn(checkIns);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        verify(checkIn).isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE, ColdMealsCounter.END_COLD_MEALS_DATE);
        assertThat(nbColdMeals).isEqualTo(1);
    }

    @Test
    public void should_have_zero_cold_meal_when_check_in_date_is_before_begin_cold_meals_date() throws Exception {
        //Arrange
        when(checkIn.isBetween(ColdMealsCounter.BEGIN_COLD_MEALS_DATE,
                ColdMealsCounter.END_COLD_MEALS_DATE)).thenReturn(false);
        when(checkInProvider.getCheckIns()).thenReturn(checkIns);

        //Act
        int nbColdMeals = counter.count();

        //Assert
        assertThat(nbColdMeals).isEqualTo(0);
    }
}