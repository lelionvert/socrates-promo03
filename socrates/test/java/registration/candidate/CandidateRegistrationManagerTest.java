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
        emptyInMemoryCandidateRegistrationManager = new EmptyInMemoryCandidateRegistrationManager();
        alreadyFilledInMemoryCandidateRegistrationManager = new AlreadyFilledInMemoryCandidateRegistrationManager();
    }

    @Test
    public void getEmails_should_return_no_email_when_no_email_exists() {
        Collection<String> emails = emptyInMemoryCandidateRegistrationManager.getEmails();
        Assertions.assertThat(emails)
                  .hasSize(0);
    }

    @Test
    public void getEmails_should_return_one_email_when_one_email_exists() {
        Collection<String> emails = alreadyFilledInMemoryCandidateRegistrationManager.getEmails();
        Assertions.assertThat(emails)
                  .containsExactly("regis.dubois@socrates.com")
                  .hasSize(1);
    }

    @Test
    public void getEmails_should_return_one_candidate_when_addCandidate_to_empty() throws Exception {
        emptyInMemoryCandidateRegistrationManager.addCandidate(Email.of("jules.fournier@xp.com"));
        Assertions.assertThat(emptyInMemoryCandidateRegistrationManager.getEmails())
                  .containsExactly("jules.fournier@xp.com")
                  .hasSize(1);
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