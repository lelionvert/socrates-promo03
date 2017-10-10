import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Candidate> candidates;

    public CandidateRegistrationManager() {
        this.candidates = new ArrayList<>();
    }

    private CandidateRegistrationManager(List<Candidate> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public static CandidateRegistrationManager withExisting(Candidate... candidates) {
        return new CandidateRegistrationManager(Arrays.asList(candidates));
    }

    public Collection<Email> findEmails() {
        List<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getEmail());
        }
        return Collections.unmodifiableCollection(emails);
    }

    public Collection<Candidate> findCandidates() {
        return Collections.unmodifiableCollection(candidates);
    }

    public void addCandidates(Candidate... candidates) {
        for (Candidate candidate : candidates){
            addCandidate(candidate);
        }
    }

    private void addCandidate(Candidate candidate) {
        if (!candidates.contains(candidate)) {
            candidates.add(candidate);
        }
    }
}
