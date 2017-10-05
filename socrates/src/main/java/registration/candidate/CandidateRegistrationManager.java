package registration.candidate;

import java.util.Collection;

public interface CandidateRegistrationManager {

    Collection<String> getEmails();

    void addCandidate(Email email);

    void addCandidates(String... emails);
}
