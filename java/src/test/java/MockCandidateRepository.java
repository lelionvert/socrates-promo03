import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class MockCandidateRepository implements CandidateRepository{

    private boolean isGetEmailCalled;
    private boolean isAddCalled;
    private boolean isMethodHasAlreadyCalled;

    @Override
    public void add(Candidate candidate) {
        isAddCalled = true;
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        isMethodHasAlreadyCalled = true;
        return true;
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
