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

    public Collection<Email> findEmails() {
        Collection<Email> candidatesEmailsFound = new ArrayList<>();
        candidatesEmailsFound.addAll(candidates);
        return candidatesEmailsFound;
    }

    public void addMany(Email... emails) {
        for (Email email : emails){
            add(email);
        }
    }

    private void add(Email email) {
        if(!candidates.contains(email)) {
            candidates.add(email);
        }

    }
}
