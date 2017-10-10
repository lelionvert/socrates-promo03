import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MockCandidateRepository implements CandidateRepository{

    private Collection<Email> emails = new ArrayList<>();

    @Override
    public void add(Candidate candidate) {

    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        return emails;
    }
}
