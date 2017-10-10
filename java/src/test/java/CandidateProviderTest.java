import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CandidateProviderTest {
    private CandidateProvider candidateProvider;

    @Before
    public void setUp() throws Exception {
        candidateProvider = new CandidateProvider();
    }

    @Test
    public void should_have_no_email_at_initialization() {
        Assertions.assertThat(candidateProvider.isEmpty()).isTrue();
    }
}