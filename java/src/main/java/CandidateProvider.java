import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class CandidateProvider {

    private Collection<Email> emails = new HashSet<>();

    public boolean isEmpty() {
        return true;
    }

    public void add(Email... emails) {
        for (Email candidateEmail : emails) {
            this.emails.add(candidateEmail);
        }
    }

    public boolean exist(Email... emails) {
        for (Email candidateEmail : emails) {
            if(!this.emails.contains(candidateEmail))
                return false;
        }
        return true;
    }

    public int size() {
        return emails.size();
    }

    public Collection<Email> getEmails() {
        return Collections.unmodifiableCollection(emails);
    }
}
