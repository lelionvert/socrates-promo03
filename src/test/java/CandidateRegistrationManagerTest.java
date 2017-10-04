import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void noExistingAndAddingZeroCandidate() {
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }
}
