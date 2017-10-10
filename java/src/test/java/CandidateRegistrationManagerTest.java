import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateRegistrationManagerTest {

    private static final Email SABINE_MAIL = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_MAIL = Email.of("melody@lcdlv.fr");
    private static final Email CYRIL_MAIL = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_MAIL = Email.of("ismael@lcdlv.fr");

    private CandidateRegistrationManager candidateRegistrationManager;

    @Before
    public void setUp() throws Exception {
        candidateRegistrationManager = new CandidateRegistrationManager();
    }

    @Test
    public void should_not_have_any_email_at_initialization() {
        assertThat(candidateRegistrationManager.findEmails()).isEmpty();
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_emails() {
        candidateRegistrationManager.addMany(Candidate.of(SABINE_MAIL));
        assertThat(candidateRegistrationManager.findEmails())
                  .containsOnlyOnce(SABINE_MAIL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect() {
        candidateRegistrationManager.addMany(Candidate.of(SABINE_MAIL));
        candidateRegistrationManager.findEmails().clear();
    }

    @Test
    public void should_find_several_when_adding_several_given_no_existing_emails() {
        candidateRegistrationManager.addMany(Candidate.of(CYRIL_MAIL), Candidate.of(ISMAEL_MAIL));
        assertThat(candidateRegistrationManager.findEmails())
                  .containsExactlyInAnyOrder(CYRIL_MAIL, ISMAEL_MAIL);
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(Candidate.of(SABINE_MAIL), Candidate.of(MELODY_MAIL));
        candidateRegistrationManager.addMany(Candidate.of(CYRIL_MAIL));

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_MAIL, CYRIL_MAIL, MELODY_MAIL);
    }

    @Test
    public void should_find_several_plus_n_emails_when_adding_n_emails_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(Candidate.of(SABINE_MAIL), Candidate.of(MELODY_MAIL));
        candidateRegistrationManager.addMany(Candidate.of(CYRIL_MAIL), Candidate.of(ISMAEL_MAIL));

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_MAIL, MELODY_MAIL, CYRIL_MAIL, ISMAEL_MAIL);
    }

    @Test
    public void should_not_add_an_existing_candidate_email() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(Candidate.of(SABINE_MAIL), Candidate.of(MELODY_MAIL));
        candidateRegistrationManager.addMany(Candidate.of(SABINE_MAIL));

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_MAIL, MELODY_MAIL);
    }
}
