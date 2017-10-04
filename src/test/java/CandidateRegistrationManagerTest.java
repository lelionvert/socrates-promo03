import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CandidateRegistrationManagerTest {

    private CandidateRegistrationManager candidateRegistrationManager;
    private List<String> candidatesEmail;

    @Before
    public void setUp() throws Exception {
        candidateRegistrationManager = new CandidateRegistrationManager();
        candidatesEmail = new ArrayList<String>();
    }

    @Test
    public void noExistingAndAddingZeroCandidate() {
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        candidatesEmail.add("test@test.fr");

        candidateRegistrationManager.add(candidatesEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .hasSize(1)
                  .containsAll(candidatesEmail);
    }

    @Test
    public void noExistingAndAddingTwoCandidates() {
        candidatesEmail.add("test@test.fr");
        candidatesEmail.add("test1@test.fr");

        candidateRegistrationManager.add(candidatesEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .hasSize(2)
                  .containsAll(candidatesEmail);
    }

    @Test
    public void twoExistingAndAddingOneCandidate(){
        candidatesEmail.add("test@test.fr");
        candidatesEmail.add("test1@test.fr");
        candidateRegistrationManager = new CandidateRegistrationManager(candidatesEmail);

        String newCandidateEmail = "test2@test.fr";
        candidateRegistrationManager.add(newCandidateEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
            .hasSize(3)
            .containsAll(candidatesEmail)
            .contains(newCandidateEmail);
    }
}
