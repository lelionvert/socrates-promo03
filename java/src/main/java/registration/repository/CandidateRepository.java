package registration.repository;

import registration.model.Candidate;
import shared.model.Email;

import java.util.Collection;

public interface CandidateRepository {
    void add(Candidate candidate);

    boolean hasAlready(Candidate candidate);

    Collection<Email> getEmails();
}
