import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashSet;

public class CandidateProvider {

    private Collection<Email> emails = new HashSet<>();

    public boolean isEmpty() {
        return true;
    }

    public boolean exist(Email email) {
        return emails.contains(email);
    }


    public void add(Email email) {
        emails.add(email);
    }
}
