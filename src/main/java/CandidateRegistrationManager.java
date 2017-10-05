import java.util.*;

public class CandidateRegistrationManager {

    Set<Email> candidates;

    public CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new HashSet<Email>();
    }

    public Collection<Email> findAllEmail() {
        return candidates;
    }

    public void addAll(List<Email> emails) {
        candidates.addAll(emails);
    }

    public void add(Email email) {
        candidates.add(email);
    }
}
