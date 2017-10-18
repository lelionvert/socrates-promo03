package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CandidateRegistrationManager {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateRegistrationManager(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateRegistrationManager() {
    }

    public Collection<Email> findEmails() {
        return candidateRepository.getEmails();
    }

    public Candidate findByEmail(String email){
        return candidateRepository.getByEmail(email);
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
