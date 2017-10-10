package registration.repository;

import registration.model.Candidate;
import shared.model.Email;

import java.util.*;

public class InMemoryCandidateRepository implements CandidateRepository {

    private final Collection<Candidate> candidates;

    private InMemoryCandidateRepository(Collection<Candidate> candidates) {
        this();
        this.candidates.addAll(candidates);
    }

    public static CandidateRepository withExisting(Candidate... candidates) {
        return new InMemoryCandidateRepository(Arrays.asList(candidates));
    }

    public InMemoryCandidateRepository() {
        this.candidates = new ArrayList<>();
    }

    @Override
    public void add(Candidate candidate) {
            candidates.add(candidate);
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return candidates.contains(candidate);
    }

    @Override
    public Collection<Email> getEmails() {
        List<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getEmail());
        }
        return Collections.unmodifiableCollection(emails);
    }
}
