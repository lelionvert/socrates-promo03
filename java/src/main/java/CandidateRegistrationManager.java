import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Candidate> candidates;

    private CandidateRegistrationManager(List<Candidate> existingCandidates) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidates = new ArrayList<>();
    }

    public static CandidateRegistrationManager withExisting(Candidate... existingCandidates) {
        return new CandidateRegistrationManager(Arrays.asList(existingCandidates));
    }



    public Collection<Email> findEmails() {
        ArrayList<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getMail());
        }
        return Collections.unmodifiableCollection(emails);
    }

    public void addMany(Candidate... candidates) {
        for (Candidate candidate : candidates){
            add(candidate);
        }
    }

    private void add(Candidate candidate) {
        if(!candidates.contains(candidate)) {
            candidates.add(candidate);
        }

    }
}
