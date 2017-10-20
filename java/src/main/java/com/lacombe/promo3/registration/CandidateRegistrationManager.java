package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.*;

public class CandidateRegistrationManager {

    private final CandidateRepository candidateRepository;

    public CandidateRegistrationManager(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Collection<Email> findEmails() {
        return candidateRepository.getEmails();
    }

    public boolean register(Candidate... candidates) {
        boolean allIsAdded = true;
        for (Candidate candidate : candidates){
            if(!registerOne(candidate))
                allIsAdded = false;
        }
        return allIsAdded;
    }

    private boolean registerOne(Candidate candidate) {
        boolean isAdded = false;
        if (!candidateRepository.hasAlready(candidate)) {
            candidateRepository.add(candidate);
            isAdded = true;
        }
        return isAdded;
    }
}
