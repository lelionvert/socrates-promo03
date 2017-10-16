package com.lacombe.promo3;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class InMemoryCheckInProviderTest {

    private CheckIn regisCheckIn;

    @Before
    public void setUp() throws Exception {
        regisCheckIn = mock(CheckIn.class);
    }

    @Test
    public void should_call_method_isLate_when_call_method_count_checkins_late() throws Exception {
        //Arrange
        when(regisCheckIn.isLate()).thenReturn(true);
        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(regisCheckIn);

        //Act
        int nbCheckIns = checkInProvider.countLateCheckIns();

        //Assert
        verify(regisCheckIn).isLate();
        Assertions.assertThat(nbCheckIns).isEqualTo(1);
    }

    @Test
    public void should_have_zero_check_in_late_when_check_in_date_is_not_given() throws Exception {
        //Arrange
        when(regisCheckIn.getCheckInDate()).thenReturn(null);
        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(regisCheckIn);

        //Act
        int nbCheckIns = checkInProvider.countLateCheckIns();

        //Assert
        Assertions.assertThat(nbCheckIns).isEqualTo(0);
    }


}
