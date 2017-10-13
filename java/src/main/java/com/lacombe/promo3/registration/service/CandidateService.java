package com.lacombe.promo3.registration.service;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class CandidateService {

    public Optional<Candidate> getItem(String email) {
        Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
        Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
        Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
        Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
        List<Candidate> candidates = Arrays.asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
        for (Candidate candidate : candidates) {
            if(candidate.getEmail().equals(Email.of(email))) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    public Collection<Candidate> getCandidates() {
        Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
        Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
        Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
        Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
        return Arrays.asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    }
}
