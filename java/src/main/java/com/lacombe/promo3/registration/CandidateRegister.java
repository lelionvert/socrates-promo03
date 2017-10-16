package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.*;

public class CandidateRegister {

    private final CandidateRepository candidateRepository;

    public CandidateRegister(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Collection<Email> findEmails() {
        return candidateRepository.getEmails();
    }

    public void register(Candidate... candidates) {
        for (Candidate candidate : candidates){
            registerOne(candidate);
        }
    }

    private void registerOne(Candidate candidate) {
        if (!candidateRepository.hasAlready(candidate)) {
            candidateRepository.add(candidate);
        }
    }
}
