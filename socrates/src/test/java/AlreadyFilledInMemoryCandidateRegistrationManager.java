public class AlreadyFilledInMemoryCandidateRegistrationManager extends InMemoryCandidateRegistrationManager {

    public static String /*List*/ existingCandidates;

    static {
        existingCandidates = "regis.dubois@socrates.com";
    }

    public AlreadyFilledInMemoryCandidateRegistrationManager() {
        super(existingCandidates);
    }
}
