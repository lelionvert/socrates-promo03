import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Email> candidates;

    private CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new ArrayList<>();
    }

    public static CandidateRegistrationManager withExisting(Email... existingCandidates) {
        return new CandidateRegistrationManager(Arrays.asList(existingCandidates));
    }

    public Collection<Email> findAllEmail() {
        return candidates;
    }

    public void addMany(Email... emails) {
        for (Email email : emails){
            add(email);
        }
    }

    public void add(Email email) {
        if(candidates.contains(email)) {
            throw new CandidateRegistrationException("L'email "+email+" est déjà utilisé pour une candidature.");
        }
        candidates.add(email);
    }
}
