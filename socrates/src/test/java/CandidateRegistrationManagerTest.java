import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void getNoEmailWhenNoEmailExists() {
        Assert.assertEquals("", new CandidateRegistrationManager("").getEmails());
    }

    @Test
    public void getOneEmailWhenOneEmailExists() {
        Assert.assertEquals("regis.dubois@socrates.com", new CandidateRegistrationManager("regis.dubois@socrates.com").getEmails());
    }

}