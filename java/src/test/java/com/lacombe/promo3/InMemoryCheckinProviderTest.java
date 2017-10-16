package com.lacombe.promo3;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class InMemoryCheckinProviderTest {

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
        int nbCheckins = checkInProvider.countLateCheckins();

        //Assert
        verify(regisCheckIn).isLate();
        Assertions.assertThat(nbCheckins).isEqualTo(1);
    }
}
