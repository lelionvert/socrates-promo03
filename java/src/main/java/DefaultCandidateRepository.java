import java.util.*;

public class DefaultCandidateRepository implements CandidateRepository {

    private final Collection<Candidate> candidates;

    private DefaultCandidateRepository(Collection<Candidate> candidates) {
        this();
        this.candidates.addAll(candidates);
    }

    public static DefaultCandidateRepository withExisting(Candidate... candidates) {
        return new DefaultCandidateRepository(Arrays.asList(candidates));
    }

    public DefaultCandidateRepository() {
        this.candidates = new ArrayList<>();
    }

    @Override
    public void add(Candidate candidate) {
            candidates.add(candidate);
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return candidates.contains(candidate);
    }

    @Override
    public Collection<Email> getEmails() {
        List<Email> emails = new ArrayList<>();
        for (Candidate candidate : candidates) {
            emails.add(candidate.getEmail());
        }
        return Collections.unmodifiableCollection(emails);
    }
}
