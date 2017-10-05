package registration.candidate;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class CandidateRegistrationManagerTest {

    private CandidateRegistrationManager alreadyFilledInMemoryCandidateRegistrationManager;
    private CandidateRegistrationManager emptyInMemoryCandidateRegistrationManager;

    @Before
    public void setUp() throws Exception {
        alreadyFilledInMemoryCandidateRegistrationManager = new AlreadyFilledInMemoryCandidateRegistrationManager();
        emptyInMemoryCandidateRegistrationManager = new EmptyInMemoryCandidateRegistrationManager();
    }

    @Test
    public void getNoEmailWhenNoEmailExists() {
        Collection emails = emptyInMemoryCandidateRegistrationManager.getEmails();
        Assert.assertEquals(0, emails.size());
    }

    @Test
    public void getOneEmailWhenOneEmailExists() {
        Collection emails = alreadyFilledInMemoryCandidateRegistrationManager.getEmails();

        Assert.assertEquals(1, emails.size());
        Assert.assertTrue(emails.contains("regis.dubois@socrates.com"));
    }

    @Test
    public void getEmails_should_return_one_cantidate_when_addCandidate_to_empty() throws Exception {
        emptyInMemoryCandidateRegistrationManager.addCandidate(Email.of("jules.fournier@xp.com"));
        Assertions.assertThat(emptyInMemoryCandidateRegistrationManager.getEmails()).containsExactly("jules.fournier@xp.com");
    }

    /*
    @Test
    public void getSeveralEmailsWhenSeveralEmailsExist() {
        ArrayList<String> emails = new ArrayList<String>(2);
        emails.add(0,"regis.dubois@socrates.com");
        emails.add(1,"fanny.dubois@crafts.com");

        Assert.assertEquals("regis.dubois@socrates.com", new registration.candidate.CandidateRegistrationManager(emails).getEmailsString());
        Assert.assertEquals("fanny.dubois@crafts.com", new registration.candidate.CandidateRegistrationManager(emails).getEmailsString());
    }*/
}