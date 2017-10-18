package com.lacombe.promo3.registration.service;

import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import com.lacombe.promo3.registration.repository.DBCandidateRepository;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CandidateService {

    private final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);

    //private final CandidateRepository candidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateRegistrationManager candidateRegistrationManager;
    //= new CandidateRegistrationManager(candidateRepository);

    @HystrixCommand(fallbackMethod = "reliableGetItem")
    public Optional<Candidate> getItem(String email) {
        boolean isServiceOk = new Random().nextBoolean();
        if(isServiceOk) {
            return candidateRegistrationManager.findCandidate(email);
        } else {
            throw new Error("It is a Random exception to test Hystrix");
        }
        //return candidateRegistrationManager.findCandidate(email);
    }

    public Optional<Candidate> reliableGetItem(String email) {
        return Optional.empty();
    }

    public Collection<Candidate> getCandidates() {
        ArrayList<Candidate> candidates = new ArrayList<>();
        for(Email email : candidateRegistrationManager.findEmails()) {
            candidates.add(new Candidate(email));
        }
        return candidates;
    }


    public String addCandidate(String email) {
        Candidate candidate = new Candidate(Email.of(email));
        candidateRegistrationManager.register(candidate);
        return candidate.getEmail().getEmailString();
    }
}
