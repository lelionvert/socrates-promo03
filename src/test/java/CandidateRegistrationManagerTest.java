import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CandidateRegistrationManagerTest {

    @Test
    public void noExistingAndAddingZeroCandidate() {
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        Assertions.assertThat(candidateRegistrationManager.findAllEmail()).isEmpty();
    }

    @Test
    public void noExistingAndAddingOneCandidate() {
        List<String> candidatesEmail = new ArrayList<String>();
        String firstCandidateEmail = "test@test.fr";
        candidatesEmail.add(firstCandidateEmail);

        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        candidateRegistrationManager.add(candidatesEmail);
        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                    .containsExactly(candidatesEmail.get(0));
    }

    @Test
    public void noExistingAndAddingTwoCandidates() {
        List<String> candidatesEmail = new ArrayList<String>();
        candidatesEmail.add("test@test.fr");
        candidatesEmail.add("test1@test.fr");

        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager();
        candidateRegistrationManager.add(candidatesEmail);

        Assertions.assertThat(candidateRegistrationManager.findAllEmail())
                  .hasSize(2)
                  .containsAll(candidatesEmail);
    }
}
