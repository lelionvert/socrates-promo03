import java.util.*;

public class CandidateRegistrationManager {

    Set<Email> candidates;

    public CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new HashSet<>();
    }

    public Collection<Email> findAllEmail() {
        return candidates;
    }

    public void addAll(List<Email> emails) {
        emails.forEach( email ->
            add(email)
        );
    }

    public void add(Email email) {
        candidates.add(email);
    }
}
