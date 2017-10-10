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

    public void register(Candidate... candidates) {
        for (Candidate candidate : candidates){
            registerOne(candidate);
        }
    }

    private void registerOne(Candidate candidate) {
        if (!candidates.contains(candidate)) {
            candidates.add(candidate);
        }
    }
}
