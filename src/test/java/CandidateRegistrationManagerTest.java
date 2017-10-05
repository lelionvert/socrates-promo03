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
        candidatesEmail.add(new Email("sabine@lcdlv.fr"));
        candidatesEmail.add(new Email("melody@lcdlv.fr"));
    }

    @Test
    public void noExistingAndAddingZeroCandidate() {
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        Email email = new Email("sabine@lcdlv.fr");
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
        Email newCandidateEmail = new Email("cyril@lcdlv.fr");
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
        newCandidatesEmail.add(new Email("cyril@lcdlv.fr"));
        newCandidatesEmail.add(new Email("ismael@lcdlv.fr"));
        candidateRegistrationManager.addAll(newCandidatesEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(4)
            .containsAll(candidatesEmail)
            .containsAll(newCandidatesEmail);
    }
    @Test
    public void twoExistingAndAddingOneCandidateAlreadyExisting(){
        candidateRegistrationManager = new CandidateRegistrationManager(candidatesEmail);
        Email existingCandidateEmail = new Email("sabine@lcdlv.fr");
        candidateRegistrationManager.add(existingCandidateEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(2)
            .containsAll(candidatesEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyEmailThrowException() throws Exception {
        new Email("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailThrowException() throws Exception {
        new Email(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmailValidThrowException() throws Exception {
        new Email("emailNotValid.ok");
    }
}
