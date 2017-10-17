
package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
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
        ColdMealsCounter counter = new ColdMealsCounter(checkInProvider);
        CheckIn regisCheckIn = CheckIn.of(Email.of("regis.dubois@socrates.com"),
                LocalDateTime.of(2017, 10, 27, 22, 0, 0, 0));
        when(checkInProvider.getCheckIns()).thenReturn(Collections.singletonList(regisCheckIn));

        //Act
        int nbColdMeals = counter.count();

        //Assert
        Assertions.assertThat(nbColdMeals).isEqualTo(1);
    }
}