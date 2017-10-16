package com.lacombe.promo3;

import java.util.Collection;

public interface CheckInProvider {
    Collection<CheckIn> getCheckIns();

    int countLateCheckIns();
}
