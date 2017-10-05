package registration.candidate;

import java.util.ArrayList;
import java.util.Collection;

public class WithBuilderInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public static Collection<String> existingCandidates;

    static {
        existingCandidates = new ArrayList<String>();
        existingCandidates.add("regis.dubois@socrates.com");
    }

    public WithBuilderInMemoryCandidateRegistrationManager() {
        super(existingCandidates);

    }
}
