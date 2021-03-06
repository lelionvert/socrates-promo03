package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateRegisterIntegrationTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_EMAIL = Email.of("melody@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_EMAIL = Email.of("ismael@lcdlv.fr");

    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL, "Sabine");
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL, "Cyril");
    private static final Candidate ISMAEL_CANDIDATE = new Candidate(ISMAEL_EMAIL, "Ismael");
    private static final Candidate MELODY_CANDIDATE = new Candidate(MELODY_EMAIL, "Melody");

    private CandidateRegister candidateRegister;
    private CandidateRegister candidateRegisterWithExistingCandidates;


    @Before
    public void setUp() throws Exception {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();
        candidateRegister = new CandidateRegister(defaultCandidateRepository);
        DefaultCandidateRepository defaultCandidateRepositoryWithExistingCandidates = DefaultCandidateRepository.withExisting(SABINE_CANDIDATE, MELODY_CANDIDATE);
        candidateRegisterWithExistingCandidates = new CandidateRegister(defaultCandidateRepositoryWithExistingCandidates);
    }

    @Test
    public void should_not_have_any_email_at_initialization() {
        assertThat(candidateRegister.findEmails()).isEmpty();
    }

    @Test
    public void should_find_one_when_adding_one_given_no_existing_candidates() {
        candidateRegister.register(SABINE_CANDIDATE);
        assertThat(candidateRegister.findEmails())
            .containsOnlyOnce(SABINE_EMAIL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_have_no_side_effect_for_candidates() {
        candidateRegister.register(SABINE_CANDIDATE);
        final Collection<Email> emails = candidateRegister.findEmails();
        emails.clear();
    }

    @Test
    public void should_find_several_candidates_when_adding_several_candidates_given_no_existing_candidates() {
        candidateRegister.register(CYRIL_CANDIDATE, ISMAEL_CANDIDATE);
        assertThat(candidateRegister.findEmails())
            .containsExactlyInAnyOrder(CYRIL_EMAIL, ISMAEL_EMAIL);
    }

    @Test
    public void should_find_several_plus_one_when_adding_one_given_several_existing_candidates() {
        candidateRegisterWithExistingCandidates.register(CYRIL_CANDIDATE);
        assertThat(candidateRegisterWithExistingCandidates.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL, CYRIL_EMAIL);
    }

    @Test
    public void should_not_add_an_existing_candidate() {
        candidateRegisterWithExistingCandidates.register(SABINE_CANDIDATE);
        assertThat(candidateRegisterWithExistingCandidates.findEmails())
            .containsExactlyInAnyOrder(SABINE_EMAIL, MELODY_EMAIL);
    }
}

