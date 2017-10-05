import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CandidateRegistrationManagerTest {

    private CandidateRegistrationManager candidateRegistrationManager;
    private List<Email> candidatesEmail;

    @Before
    public void setUp() throws Exception {
        candidateRegistrationManager = new CandidateRegistrationManager();
        candidatesEmail = new ArrayList<>();
        candidatesEmail.add(Email.of("sabine@lcdlv.fr"));
        candidatesEmail.add(Email.of("melody@lcdlv.fr"));
    }

    @Test
    public void noExistingAndAddingZeroCandidate() {
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        Email email = Email.of("sabine@lcdlv.fr");
        candidateRegistrationManager.add(email);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .hasSize(1)
                  .contains(email);
    }

    @Test
    public void noExistingAndAddingTwoCandidates() {
        candidateRegistrationManager.addAll(candidatesEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .hasSize(2)
                  .containsAll(candidatesEmail);
    }

    @Test
    public void twoExistingAndAddingOneCandidate(){
        candidateRegistrationManager = new CandidateRegistrationManager(candidatesEmail);
        Email newCandidateEmail = Email.of("cyril@lcdlv.fr");
        candidateRegistrationManager.add(newCandidateEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(3)
            .containsAll(candidatesEmail)
            .contains(newCandidateEmail);
    }

    @Test
    public void twoExistingAndAddingTwoCandidates(){
        candidateRegistrationManager = new CandidateRegistrationManager(candidatesEmail);
        List<Email> newCandidatesEmail = new ArrayList<>();
        newCandidatesEmail.add(Email.of("cyril@lcdlv.fr"));
        newCandidatesEmail.add(Email.of("ismael@lcdlv.fr"));
        candidateRegistrationManager.addAll(newCandidatesEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(4)
            .containsAll(candidatesEmail)
            .containsAll(newCandidatesEmail);
    }

    @Test
    public void twoExistingAndAddingOneCandidateAlreadyExisting() {
        candidateRegistrationManager = new CandidateRegistrationManager(candidatesEmail);
        Email existingCandidateEmail = Email.of("sabine@lcdlv.fr");
        candidateRegistrationManager.add(existingCandidateEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(2)
            .containsAll(candidatesEmail);
    }
}
