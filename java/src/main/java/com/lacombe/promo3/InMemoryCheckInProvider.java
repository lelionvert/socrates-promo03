package com.lacombe.promo3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class InMemoryCheckInProvider implements CheckInProvider {


    private Collection<CheckIn> checkIns;

    private InMemoryCheckInProvider(Collection<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static InMemoryCheckInProvider of(CheckIn... checkIns) {
        return new InMemoryCheckInProvider(Arrays.asList(checkIns));
    }


    @Override
    public Collection<CheckIn> getCheckIns() {
        return Collections.unmodifiableCollection(checkIns);
    }

    @Override
    public int countLateCheckins() {
        return 0;
    }
}
