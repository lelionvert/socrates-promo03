package com.lacombe.promo3.registration.repository.spring;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Repository //"NOMS" si plusieurs repo
public class JpaCandidateRepository implements CandidateRepository {
    private JpaCandidateEntityRepository jpaCandidateEntityRepository;

    public JpaCandidateRepository(JpaCandidateEntityRepository jpaCandidateEntityRepository) {
        this.jpaCandidateEntityRepository = jpaCandidateEntityRepository;
    }

    @Override
    public void add(Candidate candidate) {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setEmail(candidate.getEmail().getEmailString());
        jpaCandidateEntityRepository.save(candidateEntity);

    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        return null;
    }

    @Override
    public Collection<Candidate> getCandidates() {
        return jpaCandidateEntityRepository.findAll()
                .stream().map(x -> new Candidate(Email.of(x.toString())))
                .collect(toSet());
    }

    @Override
    public Optional<Candidate> getCandidate(String email) {
        return null;
    }
}
