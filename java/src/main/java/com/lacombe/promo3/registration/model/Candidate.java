package com.lacombe.promo3.registration.model;

import com.lacombe.promo3.registration.controller.CandidateForm;

public class Candidate {

    private Email email;

    public Candidate() {
    }

    public Candidate(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
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

    public static Candidate of(CandidateForm candidateForm) {
        return null;
    }
}