package registration.candidate;

import java.util.ArrayList;
import java.util.Collection;

public class EmptyInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public static Collection<String> existingCandidates;

    static {
        existingCandidates = new ArrayList<String>();
    }

    public EmptyInMemoryCandidateRegistrationManager() {
        super(existingCandidates);
    }
}
