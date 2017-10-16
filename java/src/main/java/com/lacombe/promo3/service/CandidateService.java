package com.lacombe.promo3.service;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Candidate addCandidate(String email) {
        final Candidate candidate = new Candidate(Email.of(email));
        candidateRepository.add(candidate);
        return candidate;
    }

    public String reliable() {
        return "Not currently working";
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String getEmail() {
        throw new RuntimeException();
    }
}
