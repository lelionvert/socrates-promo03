import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CandidateRegistrationManagerTest {

    public static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    public static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    public static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    public static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CandidateRegistrationManager candidateRegistrationManager;

    @Before
    public void setUp() throws Exception {
        candidateRegistrationManager = new CandidateRegistrationManager();
    }

    @Test
    public void noExistingAndAddingZeroCandidate() {
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        candidateRegistrationManager.add(SABINE_EMAIL);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .containsOnlyOnce(SABINE_EMAIL);
    }

    @Test
    public void noExistingAndAddingTwoCandidates() {
        candidateRegistrationManager.addMany(CYRIL_EMAIL, ISMAEL_EMAIL);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .containsExactlyInAnyOrder(CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void twoExistingAndAddingOneCandidate(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.add(CYRIL_EMAIL);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(SABINE_EMAIL, CYRIL_EMAIL, MELODY_EMAIL);
    }

    @Test
    public void twoExistingAndAddingTwoCandidates(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.addMany(CYRIL_EMAIL, ISMAEL_EMAIL);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void twoExistingAndAddingOneCandidateAlreadyExisting() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(SABINE_EMAIL, MELODY_EMAIL);
        candidateRegistrationManager.add(SABINE_EMAIL);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}
