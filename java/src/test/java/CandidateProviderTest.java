import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CandidateProviderTest {

    private CandidateProvider candidateProvider;

    public static final Email MARJORY_EMAIL = Email.of("marjoryTahLesOuf@lcdlv.fr");
    public static final Email JORDAN_EMAIL = Email.of("jordan@lcdlv.fr");

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
        candidateProvider.add(MARJORY_EMAIL);
        Assertions.assertThat(candidateProvider.exist(MARJORY_EMAIL)).isTrue();
    }

    @Test
    public void should_adding_many_candidate() throws Exception {
        candidateProvider.add(MARJORY_EMAIL, JORDAN_EMAIL);
        Assertions.assertThat(candidateProvider.exist(MARJORY_EMAIL, JORDAN_EMAIL))
                .isTrue();
    }

    @Test
    public void should_not_add_an_existing_email() throws Exception {
        candidateProvider.add(MARJORY_EMAIL, JORDAN_EMAIL);
        candidateProvider.add(MARJORY_EMAIL);
        Assertions.assertThat(candidateProvider.size()).isEqualTo(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_have_side_effect() throws Exception {
        candidateProvider.getEmails().clear();
    }
}