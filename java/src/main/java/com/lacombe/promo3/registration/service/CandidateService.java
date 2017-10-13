package com.lacombe.promo3.registration.service;

import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;


@Service
public class CandidateService {

    private final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);

    private final DefaultCandidateRepository defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    private final CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);


    public Optional<Candidate> getItem(String email) {
        return candidateRegistrationManager.findCandidate(email);
    }

    public Collection<Candidate> getCandidates() {
        Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
        Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
        Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
        Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
        return Arrays.asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    }


    public String addCandidate(String email) {
        Candidate candidate = new Candidate(Email.of(email));
        candidateRegistrationManager.register(candidate);
        return candidate.getEmail().getEmailString();
    }
}
