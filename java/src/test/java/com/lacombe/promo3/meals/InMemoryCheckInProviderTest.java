package com.lacombe.promo3.meals;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryCheckInProviderTest {

    @Mock
    private CheckIns checkIns;

    private CheckInProvider checkInProvider;

    @Test
    public void should_have_one_checkIn_when_add_a_checkin_in_an_empty_list() throws Exception {
        //Arrange
        CheckIn checkIn = MealsUtils.checkInWithoutDate("test@gmail.fr");

        when(checkIns.size()).thenReturn(1);
        checkInProvider = InMemoryCheckInProvider.of(checkIns);

        //Act
        checkInProvider.add(checkIn);

        //Assert
        verify(checkIns).add(checkIn);
        Assertions.assertThat(checkInProvider.size()).isEqualTo(1);
    }
}