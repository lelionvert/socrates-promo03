import java.util.*;

public class CandidateRegistrationManager {

    private final CandidateProvider candidateProvider;

    private CandidateRegistrationManager(Candidate... existingCandidates) {
        this();
        candidateProvider.add(existingCandidates);
    }

    public CandidateRegistrationManager() {
        candidateProvider = new CandidateProvider();
    }

    public static CandidateRegistrationManager withExisting(Candidate... existingCandidates) {
        return new CandidateRegistrationManager(existingCandidates);
    }

    public ArrayList<Email> getAllEmails() {
        ArrayList<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidateProvider.getCandidates()) {
            emails.add(candidate.getEmail());
        }
        return emails;
    }

    public void register(Candidate... candidates) {
        candidateProvider.add(candidates);
    }
}
