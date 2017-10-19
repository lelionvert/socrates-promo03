package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.CandidateEntity;
import com.lacombe.promo3.registration.model.Email;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;

@Repository
public class JpaCandidateRepository implements CandidateRepository {
    private JPACandidateEntityRepository jpaCandidateRepository;

    public JpaCandidateRepository(JPACandidateEntityRepository jpaCandidateRepository) {
        this.jpaCandidateRepository = jpaCandidateRepository;
    }

    @Override
    public void add(Candidate candidate) {
        CandidateEntity candidateEntity = new CandidateEntity(candidate.getEmail().toString());
        jpaCandidateRepository.save(candidateEntity);
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        return jpaCandidateRepository.findAll()
            .stream().map(x -> Email.valueOf(x.getEmail()))
            .collect(toSet());
    }

    @Override
    public Collection<Candidate> getCandidates() {
        return null;
    }
}
