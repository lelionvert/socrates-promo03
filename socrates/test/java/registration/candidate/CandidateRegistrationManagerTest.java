package registration.candidate;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class CandidateRegistrationManagerTest {

    private final static String EMAIL_REGIS_DUBOIS = "regis.dubois@socrates.com";
    private final static String EMAIL_JULES_FOURNIER = "jules.fournier@xp.com";
    private final static String EMAIL_JULIE_MARECHAL = "julie.marechal@microsoft.com";

    private CandidateRegistrationManager withBuilderFilledInMemoryCandidateRegistrationManager;
    private CandidateRegistrationManager emptyInMemoryCandidateRegistrationManager;

    @Before
    public void setUp() throws Exception {
        emptyInMemoryCandidateRegistrationManager = new EmptyInMemoryCandidateRegistrationManager();
        withBuilderFilledInMemoryCandidateRegistrationManager = new WithBuilderInMemoryCandidateRegistrationManager();
    }

    @Test
    public void getEmails_should_return_no_email_when_no_email_exists() {
        Collection<String> emails = emptyInMemoryCandidateRegistrationManager.getEmails();
        Assertions.assertThat(emails)
                  .hasSize(0);
    }

    @Test
    public void getEmails_should_return_one_email_when_one_email_exists() {
        Collection<String> emails = withBuilderFilledInMemoryCandidateRegistrationManager.getEmails();
        Assertions.assertThat(emails)
                  .containsExactlyInAnyOrder(EMAIL_REGIS_DUBOIS);
    }

    @Test
    public void getEmails_should_return_one_candidate_when_addCandidate_to_empty() throws Exception {
        emptyInMemoryCandidateRegistrationManager.addCandidate(Email.of("jules.fournier@xp.com"));
        Assertions.assertThat(emptyInMemoryCandidateRegistrationManager.getEmails())
                  .containsExactlyInAnyOrder(EMAIL_JULES_FOURNIER);
    }

    @Test
    public void getEmails_should_return_several_candidates_when_addCandidates_to_empty() throws Exception {
        emptyInMemoryCandidateRegistrationManager.addCandidates(EMAIL_JULES_FOURNIER, EMAIL_JULIE_MARECHAL);
        Assertions.assertThat(emptyInMemoryCandidateRegistrationManager.getEmails())
                  .containsExactlyInAnyOrder(EMAIL_JULIE_MARECHAL, EMAIL_JULES_FOURNIER);

    }
}

/* todo
    SetUp email de base constant
    Ajouter un builder
 */