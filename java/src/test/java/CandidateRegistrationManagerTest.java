import org.junit.Before;
import org.junit.Test;

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
    private static final Candidate MELODY_CANDIDATE = new Candidate(MELODY_EMAIL);

    private CandidateRegistrationManager candidateRegistrationManager;
    private CandidateRepository candidateRepository;

    @Before
    public void setUp() throws Exception {
        candidateRepository = new CandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(candidateRepository);
    }

    @Test
    public void should_not_have_any_email_at_initialization() {
        assertThat(candidateRegistrationManager.findEmails()).isEmpty();
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_candidates() {
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
            .containsOnlyOnce(SABINE_EMAIL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect_for_candidates() {
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        final Collection<Email> emails = candidateRegistrationManager.findEmails();
        emails.clear();
    }

    @Test
    public void should_find_several_candidates_when_adding_several_candidates_given_no_existing_candidates() {
        candidateRegistrationManager.register(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_candidates() {
        CandidateRepository anotherCandidateRepository = CandidateRepository.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        CandidateRegistrationManager anotherCandidateRegistrationManager = new CandidateRegistrationManager(anotherCandidateRepository);
        anotherCandidateRegistrationManager.register(CYRIL_CANDIDATE);

        assertThat(anotherCandidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL);
    }

    @Test
    public void should_not_add_an_existing_candidate() {
        CandidateRepository candidateRepository = CandidateRepository.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager(candidateRepository);
        candidateRegistrationManager.register(SABINE_CANDIDATE);

        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}