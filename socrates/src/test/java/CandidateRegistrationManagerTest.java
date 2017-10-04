import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void getNoEmailWhenNoEmailAdded() {
        Assert.assertEquals("", new CandidateRegistrationManager("").getEmails());
    }

    @Test
    public void getOneEmailWhenOneEmailAdded() {
        Assert.assertEquals("regis.dubois@socrates.com", new CandidateRegistrationManager("regis.dubois@socrates.com").getEmails());
    }
}