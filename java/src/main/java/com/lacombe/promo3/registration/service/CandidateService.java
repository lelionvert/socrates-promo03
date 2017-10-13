package com.lacombe.promo3.registration.service;

import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CandidateService {

    public static final Email LUCAS_EMAIL = Email.of("lucas@lcdlv.fr");
    public static final Candidate LUCAS_CANDIDATE = new Candidate(LUCAS_EMAIL);
    public static final Email JULIE_EMAIL = Email.of("julie@lcdlv.fr");
    public static final Candidate JULIE_CANDIDATE = new Candidate(JULIE_EMAIL);

    private final CandidateRegistrationManager candidateRegistrationManager;

    public CandidateService(){
        DefaultCandidateRepository defaultCandidateRepository =
                DefaultCandidateRepository.withExisting(LUCAS_CANDIDATE, JULIE_CANDIDATE);
        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);
    }

    public Collection<Email> getEmails(){
        return candidateRegistrationManager.findEmails();
    }

    public Candidate add(String email){
        return new ArrayList<>(
                candidateRegistrationManager.register(new Candidate(Email.of(email))))
                .get(0);
    }
}
