import java.util.*;

public class CandidateRegistrationManager {

    private final CandidateProvider candidateProvider;

        private CandidateRegistrationManager(Email... existingCandidates) {
        this();
        candidateProvider.add(existingCandidates);
    }

    public CandidateRegistrationManager() {
        candidateProvider = new CandidateProvider();
    }

    public static CandidateRegistrationManager withExisting(Email... existingCandidates) {
        return new CandidateRegistrationManager(existingCandidates);
    }

    public Collection<Email> findAllEmail() {
        return candidateProvider.getEmails();
    }

    public void add(Email... emails) {
        candidateProvider.add(emails);
    }
}
