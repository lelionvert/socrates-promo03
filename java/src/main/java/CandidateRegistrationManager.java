import java.util.*;

public class CandidateRegistrationManager {

    private Set<Email> candidates;

    private CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new HashSet<>();
    }

    public static CandidateRegistrationManager withExisting(Email... existingCandidates) {
        return new CandidateRegistrationManager(Arrays.asList(existingCandidates));
    }

    public Collection<Email> findAllEmail() {
        return new ArrayList<>(candidates);
    }

    public void add(Email... emails) {
        for (Email email : emails){
            candidates.add(email);
        }
    }
}
