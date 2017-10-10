import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    private CandidateRegistrationManager candidateRegistrationManager;

    @Before
    public void setUp() throws Exception {
        candidateRegistrationManager = new CandidateRegistrationManager();
    }

    @Test
    public void should_have_all_emails_when_adding_emails_to_existing_emails(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(Candidate.ofCandidate(SABINE_EMAIL), Candidate.ofCandidate(MELODY_EMAIL));
        candidateRegistrationManager.add(Candidate.ofCandidate(CYRIL_EMAIL), Candidate.ofCandidate(ISMAEL_EMAIL));

        assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_have_to_add_distinct_emails() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(Candidate.ofCandidate(SABINE_EMAIL), Candidate.ofCandidate(MELODY_EMAIL));
        candidateRegistrationManager.add(Candidate.ofCandidate(SABINE_EMAIL));
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}
