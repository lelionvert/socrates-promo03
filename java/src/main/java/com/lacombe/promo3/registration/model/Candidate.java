package com.lacombe.promo3.registration.model;

public class Candidate {

    private final Email email;

    private final String firstName;

    public Candidate(Email email, String firstName) {
        this.email = email;
        this.firstName = firstName;
    }

    public Email getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return email != null ? email.equals(candidate.email) : candidate.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
