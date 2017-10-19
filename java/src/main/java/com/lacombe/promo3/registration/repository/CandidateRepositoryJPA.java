package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.entity.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;

@Repository
public class CandidateRepositoryJPA implements CandidateRepository {

    @Autowired
    JPACandidateEntityRepository jpaCandidateEntityRepository;


    @Override
    public void add(Candidate candidate) {
        CandidateEntity candidateEntity = candidate.toCandidateEntity();
        jpaCandidateEntityRepository.save(
                candidateEntity
        );
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        Collection<CandidateEntity> candidateEntities =
                jpaCandidateEntityRepository.findAll();
        return candidateEntities.stream().map(CandidateEntity::toEmail).collect(toSet());
    }

    @Override
    public Candidate getByEmail(String email) {
        CandidateEntity candidateEntity = jpaCandidateEntityRepository.findByEmail(email);
        return candidateEntity.toCandidate();
    }
}
