package registration.candidate;

import java.util.Collection;

/**
 * Created by lenovo_1 on 04/10/2017.
 */
public interface CandidateRegistrationManager {
    String getEmailsString();

    Collection getEmails();

    void addCandidate(Email email);
}
