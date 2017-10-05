package registration.candidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class InMemoryCandidateRegistrationManager implements CandidateRegistrationManager {

    private Collection<String> emails;

    public InMemoryCandidateRegistrationManager() {
        emails = new ArrayList<String>();
    }

    protected InMemoryCandidateRegistrationManager(Collection existingCandidates) {
        this();
        this.emails.addAll(existingCandidates);
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
