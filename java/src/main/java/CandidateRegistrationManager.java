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
        return new ArrayList<>(candidates);
    }

    public void addMany(Email... emails) {
        for (Email email : emails){
            add(email);
        }
    }

    public void add(Email email) {
        if(! candidates.contains(email)) {
            candidates.add(email);
        }
    }
}
