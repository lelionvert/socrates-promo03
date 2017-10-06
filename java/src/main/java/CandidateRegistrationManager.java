import java.util.*;

public class CandidateRegistrationManager {

    private final Set<Email> candidates;

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
        candidates.addAll(Arrays.asList(emails));
    }
}
