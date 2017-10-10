import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    public static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    public static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    public static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    public static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    public static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
    private static final Candidate ISMAEL_CANDIDATE = new Candidate(ISMAEL_EMAIL);

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
    public void should_not_have_any_candidate_at_initialization() {
        assertThat(candidateRegistrationManager.findCandidates()).isEmpty();
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_emails() {
        candidateRegistrationManager.addMany(SABINE_EMAIL);
        assertThat(candidateRegistrationManager.findEmails())
                  .containsOnlyOnce(SABINE_EMAIL);
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_candidates() {
        candidateRegistrationManager.addCandidates(SABINE_CANDIDATE);
        assertThat(candidateRegistrationManager.findCandidates())
            .containsOnlyOnce(new Candidate(SABINE_EMAIL));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect() {
        candidateRegistrationManager.addMany(SABINE_EMAIL);
        final Collection<Email> emails = candidateRegistrationManager.findEmails();
        emails.clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect_for_candidates() {
        candidateRegistrationManager.addCandidates(SABINE_CANDIDATE);
        final Collection<Candidate> candidates = candidateRegistrationManager.findCandidates();
        candidates.clear();
    }

    @Test
    public void should_find_several_when_adding_several_given_no_existing_emails() {
        candidateRegistrationManager.addMany(CYRIL_EMAIL, ISMAEL_EMAIL);
        assertThat(candidateRegistrationManager.findEmails())
                  .containsExactlyInAnyOrder(CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_find_several_candidates_when_adding_several_candidates_given_no_existing_candidates() {
        candidateRegistrationManager.addCandidates(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
        assertThat(candidateRegistrationManager.findCandidates())
            .containsExactlyInAnyOrder(new Candidate(CYRIL_EMAIL), new Candidate(ISMAEL_EMAIL));
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.addMany(CYRIL_EMAIL);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, CYRIL_EMAIL, MELODY_EMAIL);
    }

    @Test
    public void should_find_several_plus_n_emails_when_adding_n_emails_given_several_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.addMany(CYRIL_EMAIL, ISMAEL_EMAIL);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_not_add_an_existing_candidate_email() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.addMany(SABINE_EMAIL);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}
