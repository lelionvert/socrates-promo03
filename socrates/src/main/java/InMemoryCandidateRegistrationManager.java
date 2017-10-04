public class InMemoryCandidateRegistrationManager implements CandidateRegistrationManager {

    private String emails;

    public InMemoryCandidateRegistrationManager() {
        this.emails = "";
    }

    public InMemoryCandidateRegistrationManager(String emails) {
        this.emails = emails;
    }

    public String getEmails() {
        return this.emails;
    }
}
