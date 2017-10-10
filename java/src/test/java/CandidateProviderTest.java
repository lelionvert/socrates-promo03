import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CandidateProviderTest {

    private CandidateProvider candidateProvider;

    public static final Email TOTO_EMAIL = Email.of("totoTahLesOuf@lcdlv.fr");
    public static final Email TATA_EMAIL = Email.of("tata@lcdlv.fr");

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
        candidateProvider.add(TOTO_EMAIL);
        Assertions.assertThat(candidateProvider.exist(TOTO_EMAIL)).isTrue();
    }

    @Test
    public void should_adding_many_candidate() throws Exception {
        candidateProvider.add(TOTO_EMAIL, TATA_EMAIL);
        Assertions.assertThat(candidateProvider.exist(TOTO_EMAIL, TATA_EMAIL))
                .isTrue();
    }

    @Test
    public void should_not_add_an_existing_email() throws Exception {
        candidateProvider.add(TOTO_EMAIL, TATA_EMAIL);
        candidateProvider.add(TOTO_EMAIL);
        Assertions.assertThat(candidateProvider.size()).isEqualTo(2);
    }
}