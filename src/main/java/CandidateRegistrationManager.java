import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Email> candidates;

    public CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new ArrayList<>();
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
        if(candidates.contains(email)) {
            throw new CandidateRegistrationException("L'email "+email+" est déjà utilisé pour une candidature.");
        }
        candidates.add(email);
    }
}
