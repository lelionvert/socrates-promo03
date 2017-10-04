import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void noExistingAndAddingZeroCandidate() {
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        String email = "test@test.fr";
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        candidateRegistrationManager.add(email);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).containsExactly(email);
    }
}
