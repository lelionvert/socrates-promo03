package com.lacombe.promo3.meals;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryCheckInProviderTest {

    @Mock
    private CheckIns checkIns;

    private CheckInProvider checkInProvider;

    @Before
    public void setUp() throws Exception {
        checkInProvider = InMemoryCheckInProvider.of(checkIns);
    }

    @Test
    public void should_have_one_checkIn_when_add_a_checkin_in_an_empty_list() throws Exception {
        //Arrange
        CheckIn checkIn = MealsUtils.checkInWithoutDate("test@gmail.fr");
        doCallRealMethod().when(checkIns).add(checkIn);
        when(checkIns.size()).thenReturn(1);

        //Act
        checkInProvider.add(checkIn);

        //Assert
        verify(checkIns).add(checkIn);
        Assertions.assertThat(checkInProvider.size()).isEqualTo(1);
    }
}