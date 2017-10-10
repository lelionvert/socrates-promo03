package registration.repository;

import registration.model.Candidate;
import shared.model.Email;

import java.util.*;

public class CandidateRepository {

    private final Collection<Candidate> candidates;

    private CandidateRepository(Collection<Candidate> candidates) {
        this();
        this.candidates.addAll(candidates);
    }

    public static CandidateRepository withExisting(Candidate... candidates) {
        return new CandidateRepository(Arrays.asList(candidates));
    }

    public CandidateRepository() {
        this.candidates = new ArrayList<>();
    }

    public void add(Candidate candidate) {
            candidates.add(candidate);
    }

    public boolean hasAlready(Candidate candidate) {
        return candidates.contains(candidate);
    }

    public Collection<Email> getEmails() {
        List<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getEmail());
        }
        return Collections.unmodifiableCollection(emails);
    }
}
