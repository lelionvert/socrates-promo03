package com.lacombe.promo3.meals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistrationBook {
    private final Collection<CheckIn> checkIns;

    private RegistrationBook(Collection<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static RegistrationBook of(CheckIn... checkIns) {
        return new RegistrationBook(Arrays.asList(checkIns));
    }

    Integer countBetween(LocalDateTime begin, LocalDateTime end) {
        //TODO remplacer le collect par count
        if(isEmpty()) return 0;
        return checkIns.stream().filter(checkIn -> checkIn.isBetween(begin, end)).collect(Collectors.toList()).size();
    }

    void add(CheckIn checkIn) {
        CheckIn existingCheckIn = getCheckInForEmail(checkIn);

        if (existingCheckIn != null) {
            checkIns.remove(existingCheckIn);
        }

        checkIns.add(checkIn);
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
}
