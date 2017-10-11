package registration;

import org.junit.Before;
import org.junit.Test;
import registration.controller.CandidateRegistrationManager;
import registration.model.Candidate;
import registration.repository.CandidateRepository;
import registration.repository.InMemoryCandidateRepository;
import shared.model.Email;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegistrationManagerTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL);
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL);
    private static final Candidate ISMAEL_CANDIDATE = new Candidate(ISMAEL_EMAIL);
    private static final Candidate MELODY_CANDIDATE = new Candidate(MELODY_EMAIL);

    private CandidateRegistrationManager candidateRegistrationManager;
    private CandidateRegistrationManager candidateRegistrationManagerWithExistingCandidates;


    @Before
    public void setUp() throws Exception {
        CandidateRepository candidateRepository = new InMemoryCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(candidateRepository);
        CandidateRepository candidateRepositoryWithExistingCandidates = InMemoryCandidateRepository.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegistrationManagerWithExistingCandidates = new CandidateRegistrationManager(candidateRepositoryWithExistingCandidates);
    }

    @Test
    public void should_not_have_any_email_at_initialization() {
        MockInMemoryCandidateRepository mockInMemoryCandidateRepository = new MockInMemoryCandidateRepository();
        CandidateRegistrationManager candidateRegistrationManager = new CandidateRegistrationManager(mockInMemoryCandidateRepository);
        assertThat(candidateRegistrationManager.findEmails()).isEmpty();
        assertThat(mockInMemoryCandidateRepository.getEmailsWasCalled).isTrue();
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_candidates() {
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
            .containsOnlyOnce(SABINE_EMAIL);
    }

    class MockInMemoryCandidateRepository implements CandidateRepository {

        public boolean getEmailsWasCalled = false;

        @Override
        public void add(Candidate candidate) {

        }

        @Override
        public boolean hasAlready(Candidate candidate) {
            return false;
        }

        @Override
        public Collection<Email> getEmails() {
            getEmailsWasCalled = true;
            return Collections.emptyList();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect_for_candidates() {
        candidateRegistrationManager.register(SABINE_CANDIDATE);
        final Collection<Email> emails = candidateRegistrationManager.findEmails();
        emails.clear();
    }

    @Test
    public void should_find_several_candidates_when_adding_several_candidates_given_no_existing_candidates() {
        candidateRegistrationManager.register(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
        assertThat(candidateRegistrationManager.findEmails())
            .containsExactlyInAnyOrder(CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_candidates() {
        candidateRegistrationManagerWithExistingCandidates.register(CYRIL_CANDIDATE);
        assertThat(candidateRegistrationManagerWithExistingCandidates.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL);
    }

    @Test
    public void should_not_add_an_existing_candidate() {
        candidateRegistrationManagerWithExistingCandidates.register(SABINE_CANDIDATE);
        assertThat(candidateRegistrationManagerWithExistingCandidates.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}