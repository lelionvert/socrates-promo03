import java.util.ArrayList;
import java.util.List;

public class CandidateRegistrationManager {

    List<Email> candidates;

    public CandidateRegistrationManager(List<Email> existingCandidates) {
        this.candidates = existingCandidates;
    }

    public CandidateRegistrationManager() {
        this.candidates = new ArrayList<Email>();
    }

    public List<Email> findAllEmail() {
        return candidates;
    }

    public void addAll(List<Email> emails) {
        candidates.addAll(emails);
    }

    public void add(Email email) {
        candidates.add(email);
    }
}
