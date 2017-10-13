package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

public class SpyCandidateRepository implements CandidateRepository {

    private int numberOfTimesMethodAddCalled;
    private int numberOfTimesMethodHasAlreadyCalled;

    @Override
    public void add(Candidate candidate) {
        numberOfTimesMethodAddCalled++;
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        numberOfTimesMethodHasAlreadyCalled++;
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
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

    public int countHowManyTimesMethodHasAlreadyBeenCalled() {
        return numberOfTimesMethodHasAlreadyCalled;
    }

    public int countHowManyTimesMethodAddBeenCalled() {
        return numberOfTimesMethodAddCalled;
    }
}
