import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void getNoEmailsWhenNoEmailsAdded() {
        Assert.assertEquals("", new CandidateRegistrationManager("").getEmails());
    }
}