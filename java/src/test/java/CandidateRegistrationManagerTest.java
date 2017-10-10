import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    public static final Candidate SABINE_CANDIDATE = Candidate.of("sabine@lcdlv.fr");
    public static final Candidate MELODY_CANDIDATE = Candidate.of("melody@lcdlv.fr");
    public static final Candidate CYRIL_CANDIDATE = Candidate.of("cyril@lcdlv.fr");
    public static final Candidate ISMAEL_CANDIDATE = Candidate.of("ismael@lcdlv.fr");

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
        candidateRegistrationManager.addMany(SABINE_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
                  .containsOnlyOnce(SABINE_CANDIDATE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect() {
        candidateRegistrationManager.addMany(SABINE_CANDIDATE);
        final Collection<Candidate> candidates = candidateRegistrationManager.findEmails();
        candidates.clear();
    }

    @Test
    public void should_find_several_when_adding_several_given_no_existing_emails() {
        candidateRegistrationManager.addMany(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
                  .containsExactlyInAnyOrder(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegistrationManager.addMany(CYRIL_CANDIDATE);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_CANDIDATE, CYRIL_CANDIDATE, MELODY_CANDIDATE);
    }

    @Test
    public void should_find_several_plus_n_emails_when_adding_n_emails_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegistrationManager.addMany(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_CANDIDATE, MELODY_CANDIDATE, CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
    }

    @Test
    public void should_not_add_an_existing_candidate_email() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegistrationManager.addMany(SABINE_CANDIDATE);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_CANDIDATE, MELODY_CANDIDATE);
    }
}
