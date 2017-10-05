package registration;

import registration.candidate.InMemoryCandidateRegistrationManager;

import java.util.ArrayList;
import java.util.Collection;

public class AlreadyFilledInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public static Collection existingCandidates;

    static {
        existingCandidates = new ArrayList<String>();
        existingCandidates.add("regis.dubois@socrates.com");
    }

    public AlreadyFilledInMemoryCandidateRegistrationManager() {
        super(existingCandidates);

    }
}
