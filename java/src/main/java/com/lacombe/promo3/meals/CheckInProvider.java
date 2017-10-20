package com.lacombe.promo3.meals;

interface CheckInProvider {
    RegistrationBook getRegistrationBook();
    void add(CheckIn checkIn);
    int size();
}
