package com.lacombe.promo3.registration.model;

import com.lacombe.promo3.registration.repository.entity.CandidateEntity;

public class Candidate {

    private Email email;

    public Candidate(Email email) {
        this.email = email;
    }

    public Candidate() {
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

    public CandidateEntity toCandidateEntity() {
        return new CandidateEntity(
                email.getEmail()
        );
    }
}
