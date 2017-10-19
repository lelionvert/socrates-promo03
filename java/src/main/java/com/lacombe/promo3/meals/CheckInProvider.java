package com.lacombe.promo3.meals;

interface CheckInProvider {
    CheckIns getCheckIns();
    void add(CheckIn checkIn);
    int size();
}
