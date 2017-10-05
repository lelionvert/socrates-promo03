package registration.candidate;

import java.util.Collection;
import java.util.Collections;

public class InMemoryCandidateRegistrationManager implements CandidateRegistrationManager {

    private Collection<String> emails;

    protected InMemoryCandidateRegistrationManager(Collection existingCandidates) {
        this.emails = existingCandidates;
    }

    public Collection<String> getEmails() {
        return this.emails;
    }

    public void addCandidate(Email email) {
        emails.add(email.getEmail());
    }

    public void addCandidates(String... emails) {
        Collections.addAll(this.emails, emails);
    }
}
