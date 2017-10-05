package registration;

import registration.candidate.InMemoryCandidateRegistrationManager;

import java.util.ArrayList;
import java.util.Collection;

public class EmptyInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public static Collection existingCandidates;

    static {
        existingCandidates = new ArrayList<String>();
    }

    public EmptyInMemoryCandidateRegistrationManager() {
        super(existingCandidates);
    }
}
