package com.lacombe.promo3;

import java.util.Collection;
import java.util.Collections;

public class InMemoryCheckInProvider implements CheckInProvider {


    private Collection<CheckIn> checkIns;

    public InMemoryCheckInProvider(Collection<CheckIn> checkIns) {
        this.checkIns = checkIns;
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
