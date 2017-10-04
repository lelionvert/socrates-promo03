import org.junit.Assert;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void getNoEmailWhenNoEmailExists() {
        Assert.assertEquals("", new InMemoryCandidateRegistrationManager().getEmails());
    }

    @Test
    public void getOneEmailWhenOneEmailExists() {
        Assert.assertEquals("regis.dubois@socrates.com", new AlreadyFilledInMemoryCandidateRegistrationManager().getEmails());
    }

    /*
    @Test
    public void getSeveralEmailsWhenSeveralEmailsExist() {
        ArrayList<String> emails = new ArrayList<String>(2);
        emails.add(0,"regis.dubois@socrates.com");
        emails.add(1,"fanny.dubois@crafts.com");

        Assert.assertEquals("regis.dubois@socrates.com", new CandidateRegistrationManager(emails).getEmails());
        Assert.assertEquals("fanny.dubois@crafts.com", new CandidateRegistrationManager(emails).getEmails());
    }*/
}