import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
    private static final Candidate ISMAEL_CANDIDATE = new Candidate(ISMAEL_EMAIL);
    private static final Candidate MELODY_CANDIDATE = new Candidate(MELODY_EMAIL);

    private CandidateRegistrationManager candidateRegistrationManager;
    private CandidateRegistrationManager candidateRegistrationManagerWithExistingCandidates;


    @Before
    public void setUp() throws Exception {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);
        DefaultCandidateRepository defaultCandidateRepositoryWithExistingCandidates = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegistrationManagerWithExistingCandidates = new CandidateRegistrationManager(defaultCandidateRepositoryWithExistingCandidates);
    }

    @Test
    public void should_call_method_getEmail_when_manager_find_emails() {
        MockCandidateRepository mockCandidateRepository = new MockCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(mockCandidateRepository);
        candidateRegistrationManager.findEmails();
        assertThat(mockCandidateRepository.isGetEmailCalled()).isTrue();
    }

    @Test
    public void should_call_method_has_already_and_add_when_manager_register_a_candidate() {
        MockCandidateRepository mockCandidateRepository = new MockCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(mockCandidateRepository);
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isTrue();
    }

    @Test
    public void should_not_add_an_existing_candidate() {
        MockCandidateRepository mockCandidateRepository = new MockCandidateRepository();
        candidateRegistrationManagerWithExistingCandidates = new CandidateRegistrationManager(mockCandidateRepository);
        candidateRegistrationManagerWithExistingCandidates.register(SABINE_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isFalse();
    }
}