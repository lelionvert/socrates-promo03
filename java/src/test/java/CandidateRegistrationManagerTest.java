import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");

    static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);

    private CandidateRegistrationManager candidateRegistrationManager;
    private MockCandidateRepository mockCandidateRepository;


    @Before
    public void setUp() throws Exception {
        mockCandidateRepository = new MockCandidateRepository(SABINE_CANDIDATE);
        candidateRegistrationManager = new CandidateRegistrationManager(mockCandidateRepository);
    }

    @Test
    public void should_call_method_get_email_when_manager_find_emails() {
        candidateRegistrationManager.findEmails();
        assertThat(mockCandidateRepository.isGetEmailCalled()).isTrue();
    }

    @Test
    public void should_call_method_has_already_and_add_when_manager_register_a_not_existing_candidate() {
        candidateRegistrationManager.register(CYRIL_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isTrue();
    }

    @Test
    public void should_call_method_has_already_and_add_when_manager_register_an_existing_candidate() {
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isFalse();
    }
}