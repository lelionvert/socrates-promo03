package com.lacombe.promo3.registration.service;

import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class CandidateService {

    private final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);

    private final DefaultCandidateRepository defaultCandidateRepository = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    private final CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);

    @ApiOperation(value = "Get a candidates", response = Candidate.class)
    public Optional<Candidate> getItem(String email) {

        List<Candidate> candidates = Arrays.asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
        for (Candidate candidate : candidates) {
            if(candidate.getEmail().equals(Email.of(email))) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    @ApiOperation(value = "Get All Candidates", response = Collection.class)
    public Collection<Candidate> getCandidates() {
        Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
        Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
        Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
        Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
        return Arrays.asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
    }

    //TODO add
}
