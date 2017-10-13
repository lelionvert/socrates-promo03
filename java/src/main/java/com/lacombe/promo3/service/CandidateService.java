package com.lacombe.promo3.service;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CandidateService {

    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    public Collection<Candidate> getCandidates() {
        return candidateRepository.getCandidates();
    }

    public void addCandidate(String email) {
        candidateRepository.add(new Candidate(Email.of(email)));
    }
}
