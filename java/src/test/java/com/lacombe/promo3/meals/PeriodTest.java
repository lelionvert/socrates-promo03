package com.lacombe.promo3.meals;

import com.lacombe.promo3.Period;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class PeriodTest {

    @Test
    public void should_be_included_in_the_period() throws Exception {
        //Arrange
        LocalDateTime begin = LocalDateTime.of(2017, 10, 20, 21, 30);
        LocalDateTime end = begin.plusDays(1);
        Period period = new Period(begin, end);

        //Act
        LocalDateTime dateTime = begin.plusHours(1);
        Boolean isIncluded = period.include(dateTime);

        //Assert
        Assertions.assertThat(isIncluded).isTrue();
    }
}
