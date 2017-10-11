import java.util.Collection;

public class MockCandidateRepository implements CandidateRepository{

    private Candidate existingCandidate;
    private boolean isGetEmailCalled;
    private boolean isAddCalled;
    private boolean isMethodHasAlreadyCalled;

    public MockCandidateRepository(Candidate candidate) {
        existingCandidate = candidate;
    }

    @Override
    public void add(Candidate candidate) {
        isAddCalled = true;
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        isMethodHasAlreadyCalled = true;
        if(existingCandidate.equals(candidate)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        isGetEmailCalled = true;
        return null;
    }

    public boolean isGetEmailCalled() {
        return isGetEmailCalled;
    }

    public boolean isAddCalled() {
        return isAddCalled;
    }

    public boolean isMethodHasAlreadyCalled() {
        return isMethodHasAlreadyCalled;
    }
}
