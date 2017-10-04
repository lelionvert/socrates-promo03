import java.util.ArrayList;
import java.util.List;

public class CandidateRegistrationManager {

    List<String> candidates = new ArrayList<String>();

    public List<String> findAllEmail() {
        return candidates;
    }

    public void add(List<String> emails) {
        candidates.addAll(emails);
    }

    public void add(String email) {
        candidates.add(email);
    }
}
