package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;

import java.util.Collection;

public interface CandidateRepository {

    void add(Candidate candidate);

    boolean hasAlready(Candidate candidate);

    Collection<Email> getEmails();
}
