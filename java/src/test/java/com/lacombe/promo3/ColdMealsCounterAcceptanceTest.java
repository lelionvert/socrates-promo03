package com.lacombe.promo3;

import com.lacombe.promo3.registration.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

public class ColdMealsCounterAcceptanceTest {

    private CheckIn regisCheckIn;
    private CheckIn fannyCheckIn;
    private CheckIn emilieCheckIn;
    private CheckIn julesCheckIn;

    @Before
    public void setUp() throws Exception {
        regisCheckIn = new CheckIn(Email.of("regis.dubois@socrates.com"));
        fannyCheckIn = new CheckIn(Email.of("fanny.dubois@crafts.com"));
        emilieCheckIn = new CheckIn(Email.of("emilie.dupuy@testing.fr"));
        julesCheckIn = new CheckIn(Email.of("jules.fournier@xp.com"));

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_have_zero_cold_meals_when_all_participants_are_not_late() throws Exception {
        regisCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 16, 30, 0, 0));
        fannyCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 18, 30, 0, 0));
        emilieCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 19, 0, 0, 0));
        julesCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 20, 45, 0, 0));

        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(regisCheckIn, fannyCheckIn, emilieCheckIn, julesCheckIn);
        ColdMealsCounter coldMealsCounter = new ColdMealsCounter(checkInProvider);

        Integer countColdMeals = coldMealsCounter.count();
        Assertions.assertThat(countColdMeals).isEqualTo(0);
    }

    @Test
    public void should_have_one_or_several_cold_meals_when_one_or_several_participants_are_late() throws Exception {
        regisCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 22, 0, 0, 0));
        fannyCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 17, 30, 0, 0));
        emilieCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 11, 0, 0, 0));
        julesCheckIn.setCheckingDate(LocalDateTime.of(2017, 10, 27, 21, 15, 0, 0));

        CheckInProvider checkInProvider = InMemoryCheckInProvider.of(regisCheckIn, fannyCheckIn, emilieCheckIn, julesCheckIn);
        ColdMealsCounter coldMealsCounter = new ColdMealsCounter(checkInProvider);

        Integer countColdMeals = coldMealsCounter.count();
        Assertions.assertThat(countColdMeals).isEqualTo(2);
    }
}
