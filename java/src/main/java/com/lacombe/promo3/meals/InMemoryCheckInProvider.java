package com.lacombe.promo3.meals;

public class InMemoryCheckInProvider implements CheckInProvider {
    private final CheckIns checkIns;

    private InMemoryCheckInProvider(CheckIns checkIns) {
        this.checkIns = checkIns;
    }

    static InMemoryCheckInProvider of(CheckIns checkIns) {
        return new InMemoryCheckInProvider(checkIns);
    }

    @Override
    public CheckIns getCheckIns() {
        return checkIns;
    }

    @Override
    public void add(CheckIn checkIn) {
        checkIns.add(checkIn);
    }

    @Override
    public int size() {
        return checkIns.size();
    }
}
