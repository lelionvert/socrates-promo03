package com.lacombe.promo3.meals;

public class InMemoryCheckInProvider implements CheckInProvider {
    private final RegistrationBook registrationBook;

    private InMemoryCheckInProvider(RegistrationBook registrationBook) {
        this.registrationBook = registrationBook;
    }

    static InMemoryCheckInProvider of(RegistrationBook registrationBook) {
        return new InMemoryCheckInProvider(registrationBook);
    }

    public RegistrationBook getRegistrationBook() {
        return registrationBook;
    }

    @Override
    public void add(CheckIn checkIn) {
        registrationBook.add(checkIn);
    }

    @Override
    public int size() {
        return registrationBook.size();
    }
}
