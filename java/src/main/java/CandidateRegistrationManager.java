import java.util.*;

public class CandidateRegistrationManager {

    private Collection<Email> candidatesEmails;
    private Collection<Candidate> candidates;


    private CandidateRegistrationManager(List<Email> existingCandidates) {
        this();
        candidatesEmails.addAll(existingCandidates);
    }

    private CandidateRegistrationManager(List<Candidate> existingCandidates, String temporaryVariable) {
        this();
        candidates.addAll(existingCandidates);
    }

    public CandidateRegistrationManager() {
        this.candidatesEmails = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public static CandidateRegistrationManager withExisting(Email... existingCandidates) {
        return new CandidateRegistrationManager(Arrays.asList(existingCandidates));
    }

    public Collection<Email> findEmails() {
        return Collections.unmodifiableCollection(candidatesEmails);
    }

    public void addMany(Email... emails) {
        for (Email email : emails){
            add(email);
        }
    }

    private void add(Email email) {
        if(!candidatesEmails.contains(email)) {
            candidatesEmails.add(email);
        }

    }

    public Collection<Candidate> findCandidates() {
        return Collections.unmodifiableCollection(candidates);
    }

    public void addCandidates(Candidate... candidates) {
        for (Candidate candidate : candidates){
            addCandidate(candidate);
        }
    }

    private void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public static CandidateRegistrationManager withExisting(Candidate... candidates) {
        return new CandidateRegistrationManager(Arrays.asList(candidates), null);
    }
}
