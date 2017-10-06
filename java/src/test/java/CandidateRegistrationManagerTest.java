import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CandidateRegistrationManagerTest {

    public static final Email sabineEmail = Email.of("sabine@lcdlv.fr");
    public static final Email melodyEmail = Email.of("melody@lcdlv.fr");
    public static final Email cyrilEmail = Email.of("cyril@lcdlv.fr");
    public static final Email ismaelEmail = Email.of("ismael@lcdlv.fr");

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
        candidateRegistrationManager.add(sabineEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .containsOnlyOnce(sabineEmail);
    }

    @Test
    public void noExistingAndAddingTwoCandidates() {
        candidateRegistrationManager.addMany(cyrilEmail, ismaelEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .containsExactlyInAnyOrder(cyrilEmail, ismaelEmail);
    }

    @Test
    public void twoExistingAndAddingOneCandidate(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(sabineEmail, melodyEmail);
        candidateRegistrationManager.add(cyrilEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(sabineEmail, cyrilEmail, melodyEmail);
    }

    @Test
    public void twoExistingAndAddingTwoCandidates(){
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(sabineEmail, melodyEmail);
        candidateRegistrationManager.addMany(cyrilEmail, ismaelEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(sabineEmail, melodyEmail, cyrilEmail, ismaelEmail);
    }

    @Test
    public void twoExistingAndAddingOneCandidateAlreadyExisting() {
        candidateRegistrationManager = CandidateRegistrationManager.withExisting(sabineEmail, melodyEmail);
        candidateRegistrationManager.add(sabineEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .containsExactlyInAnyOrder(sabineEmail, melodyEmail);
    }
}
