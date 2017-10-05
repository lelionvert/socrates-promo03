package registration.candidate;

import java.util.Collection;

public class InMemoryCandidateRegistrationManager implements CandidateRegistrationManager {

    private String emailsString;
    private Collection emails;

    protected InMemoryCandidateRegistrationManager(Collection existingCandidates) {
        this.emails = existingCandidates;
    }

    public String getEmailsString() {
        return this.emailsString;
    }

    public Collection getEmails() {
        return this.emails;
    }
}
