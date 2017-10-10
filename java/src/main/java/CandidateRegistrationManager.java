import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Email> candidatesEmails;
    private Collection<Candidate> candidates;

    private CandidateRegistrationManager(List<Candidate> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidatesEmails = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public Collection<Email> findEmails() {
        return Collections.unmodifiableCollection(candidatesEmails);
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

    public static CandidateRegistrationManager withExisting(Candidate... candidates) {
        return new CandidateRegistrationManager(Arrays.asList(candidates));
    }
}
