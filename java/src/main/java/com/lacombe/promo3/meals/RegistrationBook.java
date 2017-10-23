package com.lacombe.promo3.meals;

import com.lacombe.promo3.Period;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class RegistrationBook {
    private final Collection<CheckIn> checkIns;

    RegistrationBook(Collection<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static RegistrationBook of(CheckIn... checkIns) {
        return new RegistrationBook(new ArrayList<CheckIn>(Arrays.asList(checkIns)));
    }

    Integer countIncludedIn(Period checkinPeriod) {
        if(isEmpty()) return 0;
        return Math.toIntExact(checkIns.stream().filter(checkIn -> checkIn.isIncludedIn(checkinPeriod)).count());
    }

    CheckIn getCheckInForEmail(CheckIn checkIn) {
        Optional<CheckIn> checkInOptional = checkIns.stream().filter(existingCheckIn ->
                existingCheckIn.hasSameEmail(checkIn)).findFirst();
        return checkInOptional.orElse(null);
    }

    int size() {
        return checkIns.size();
    }


    public boolean isEmpty() {
        return checkIns.isEmpty();
    }

    public void register(CheckIn checkIn) {
        CheckIn existingCheckIn = getCheckInForEmail(checkIn);

        if (existingCheckIn != null) {
            checkIns.remove(existingCheckIn);
        }

        checkIns.add(checkIn);
    }

    public boolean contains(CheckIn checkIn) {
        return checkIns.contains(checkIn);
    }
}
