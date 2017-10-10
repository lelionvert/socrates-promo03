import java.util.Collection;

public interface CandidateRepository {

    void add(Candidate candidate);

    boolean hasAlready(Candidate candidate);

    Collection<Email> getEmails();
}
