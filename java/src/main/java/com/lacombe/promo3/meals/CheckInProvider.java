package com.lacombe.promo3.meals;

public interface CheckInProvider {
    RegistrationBook getRegistrationBook();
    void add(CheckIn checkIn);
    int size();
}
