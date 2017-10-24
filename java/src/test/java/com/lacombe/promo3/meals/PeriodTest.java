package com.lacombe.promo3.meals;

import com.lacombe.promo3.Period;
import com.lacombe.promo3.PeriodBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class PeriodTest {

    @Test
    public void should_be_included_in_the_period() throws Exception {
        //Arrange
        LocalDateTime begin = LocalDateTime.of(2017, 10, 20, 21, 30);
        LocalDateTime end = begin.plusDays(1);
        Period period = PeriodBuilder.from(begin).to(end);

        //Act
        LocalDateTime dateTime = begin.plusHours(1);
        Boolean isIncluded = period.include(dateTime);

        //Assert
        Assertions.assertThat(isIncluded).isTrue();
    }

    @Test
    public void should_not_be_included_in_the_period() throws Exception {
        //Arrange
        LocalDateTime today = LocalDateTime.of(2017, 10, 20, 21, 30);
        LocalDateTime tomorrow = today.plusDays(1);
        Period periodTodayToTomorrowAtTheSameHour = PeriodBuilder.from(today).to(tomorrow);
        //Act
        LocalDateTime aLittleBitBeforeToday = today.minusHours(1);
        Boolean isIncluded = periodTodayToTomorrowAtTheSameHour.include(aLittleBitBeforeToday);

        //Assert
        Assertions.assertThat(isIncluded).isFalse();
    }
}
