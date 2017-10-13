package com.lacombe.promo3.registration.model;

public class Candidate {

    private final Email email;

    public Candidate(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public boolean hasEmail(String email){
        return this.email.isEqualsToString(email);
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
