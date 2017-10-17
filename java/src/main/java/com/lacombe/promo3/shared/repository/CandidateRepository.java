package com.lacombe.promo3.shared.repository;

import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;

import java.util.Collection;

public interface CandidateRepository {

    void add(Candidate candidate);

    boolean hasAlready(Candidate candidate);

    Collection<Email> getEmails();
}
