package com.lacombe.promo3.shared.repository;

import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;

import java.util.*;

public class CandidateRepositoryInMemory implements CandidateRepository {

    private final Collection<Candidate> candidates;

    private CandidateRepositoryInMemory(Collection<Candidate> candidates) {
        this();
        this.candidates.addAll(candidates);
    }

    public static CandidateRepositoryInMemory withExisting(Candidate... candidates) {
        return new CandidateRepositoryInMemory(Arrays.asList(candidates));
    }

    public CandidateRepositoryInMemory() {
        this.candidates = new ArrayList<>();
    }

    @Override
    public void add(Candidate candidate) {
            candidates.add(candidate);
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return candidates.contains(candidate);
    }

    @Override
    public Collection<Email> getEmails() {
        List<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getEmail());
        }
        return Collections.unmodifiableCollection(emails);
    }
}
