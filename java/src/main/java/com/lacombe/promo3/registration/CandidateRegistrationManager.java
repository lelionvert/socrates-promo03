package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.*;

public class CandidateRegistrationManager {

    private final CandidateRepository candidateRepository;

    public CandidateRegistrationManager(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Collection<Email> findEmails() {
        return candidateRepository.getEmails();
    }

    public Collection<Candidate> register(Candidate... candidates) {
        for (Candidate candidate : candidates){
            registerOne(candidate);
        }

        return Arrays.asList(candidates);
    }

    private void registerOne(Candidate candidate) {
        if (!candidateRepository.hasAlready(candidate)) {
            candidateRepository.add(candidate);
        }
    }
}
