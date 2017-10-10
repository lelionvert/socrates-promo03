import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CandidateProviderTest {

    private CandidateProvider candidateProvider;

    public static final Candidate MARJORY_CANDIDATE = Candidate.ofCandidate(Email.of("marjoryTahLesOuf@lcdlv.fr"));
    public static final Candidate JORDAN_CANDIDATE = Candidate.ofCandidate(Email.of("jordan@lcdlv.fr"));

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
        candidateProvider.add(MARJORY_CANDIDATE);
        Assertions.assertThat(candidateProvider.exist(MARJORY_CANDIDATE)).isTrue();
    }

    @Test
    public void should_adding_many_candidate() throws Exception {
        candidateProvider.add(MARJORY_CANDIDATE, JORDAN_CANDIDATE);
        Assertions.assertThat(candidateProvider.exist(MARJORY_CANDIDATE, JORDAN_CANDIDATE))
                .isTrue();
    }

    @Test
    public void should_not_add_an_existing_email() throws Exception {
        candidateProvider.add(MARJORY_CANDIDATE, JORDAN_CANDIDATE);
        candidateProvider.add(MARJORY_CANDIDATE);
        Assertions.assertThat(candidateProvider.size()).isEqualTo(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_have_side_effect() throws Exception {
        candidateProvider.getCandidates().clear();
    }
}