package registration.candidate;

import java.util.ArrayList;
import java.util.Collection;

public class EmptyInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public EmptyInMemoryCandidateRegistrationManager() {
        super(new ArrayList());
    }
}
