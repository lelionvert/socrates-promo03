package com.lacombe.promo3.registration.unit;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.Collection;

public class MockCandidateRepository implements CandidateRepository {

    private Candidate existingCandidate;
    private boolean isGetEmailCalled;
    private boolean isAddCalled;
    private boolean isMethodHasAlreadyCalled;
    private boolean isGetByEmailCalled;

    public MockCandidateRepository(Candidate candidate) {
        existingCandidate = candidate;
}

    @Override
    public void add(Candidate candidate) {
        isAddCalled = true;
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        isMethodHasAlreadyCalled = true;
        if(existingCandidate.equals(candidate)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        isGetEmailCalled = true;
        return null;
    }

    @Override
    public Candidate getByEmail(String email) {
        isGetByEmailCalled = true;
        return null;
    }

    public boolean isGetEmailCalled() {
        return isGetEmailCalled;
    }

    public boolean isAddCalled() {
        return isAddCalled;
    }

    public boolean isMethodHasAlreadyCalled() {
        return isMethodHasAlreadyCalled;
    }

    public boolean isGetByEmailCalled(){ return isGetByEmailCalled;}
}
