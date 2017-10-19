package com.lacombe.promo3.meals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CheckIns {
    private final Collection<CheckIn> checkIns;

    private CheckIns(Collection<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static CheckIns of(CheckIn... checkIns) {
        return new CheckIns(Arrays.asList(checkIns));
    }

    Integer countBetween(LocalDateTime begin, LocalDateTime end) {
        return checkIns.stream().filter(checkIn -> checkIn.isBetween(begin, end)).collect(Collectors.toList()).size();
    }


}
