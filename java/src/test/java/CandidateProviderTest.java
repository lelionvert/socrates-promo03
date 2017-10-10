import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CandidateProviderTest {
    public static final String TOTO_EMAIL = "totoTahLesOuf@lcdlv.fr";
    private CandidateProvider candidateProvider;

    @Before
    public void setUp() throws Exception {
        candidateProvider = new CandidateProvider();
    }

    @Test
    public void should_have_no_email_at_initialization() {
        Assertions.assertThat(candidateProvider.isEmpty()).isTrue();
    }

    @Test
    public void should_adding_a_candidate() throws Exception {
        Email email = Email.of(TOTO_EMAIL);
        candidateProvider.add(email);
        Assertions.assertThat(candidateProvider.exist(email)).isTrue();

    }
}