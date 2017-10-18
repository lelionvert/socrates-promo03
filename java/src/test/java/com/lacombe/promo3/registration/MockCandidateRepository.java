package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

public class MockCandidateRepository implements CandidateRepository {

    private Candidate existingCandidate;
    private boolean isGetEmailCalled;
    private boolean isAddCalled;
    private boolean isMethodHasAlreadyCalled;

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
    public Collection<Candidate> getCandidates() {
        return null;
    }

    @Override
    public Optional<Candidate> getCandidate(String email) {
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
}