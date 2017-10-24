package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CandidateRegisterTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");

    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL, "Sabine");
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL, "Cyril");
    private static final int NUMBER_OF_TIMES_METHOD_SHOULD_BEEN_CALLED = 2;

    private CandidateRegister candidateRegister;
    private MockCandidateRepository mockCandidateRepository;
    private SpyCandidateRepository spyCandidateRepository;


    @Before
    public void setUp() throws Exception {
        mockCandidateRepository = new MockCandidateRepository(SABINE_CANDIDATE);
        spyCandidateRepository = new SpyCandidateRepository();
    }

    @Test
    public void should_call_method_get_email_when_manager_find_emails() {
        //
        candidateRegister = new CandidateRegister(mockCandidateRepository);

        //
        candidateRegister.findEmails();

        //
        assertThat(mockCandidateRepository.isGetEmailCalled()).isTrue();
    }

    @Test
    public void should_call_method_has_already_and_add_when_manager_register_a_not_existing_candidate() {
        candidateRegister = new CandidateRegister(mockCandidateRepository);
        candidateRegister.register(CYRIL_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isTrue();
    }

    @Test
    public void should_call_method_has_already_and_add_when_manager_register_an_existing_candidate() {
        candidateRegister = new CandidateRegister(mockCandidateRepository);
        candidateRegister.register(SABINE_CANDIDATE);
        assertThat(mockCandidateRepository.isMethodHasAlreadyCalled()).isTrue();
        assertThat(mockCandidateRepository.isAddCalled()).isFalse();
    }

    @Test
    public void should_call_methods_has_already_and_add_n_times_when_adding_n_candidates() throws Exception {
        candidateRegister = new CandidateRegister(spyCandidateRepository);
        candidateRegister.register(SABINE_CANDIDATE,CYRIL_CANDIDATE);
        assertThat(spyCandidateRepository.countHowManyTimesMethodHasAlreadyBeenCalled()).isEqualTo(NUMBER_OF_TIMES_METHOD_SHOULD_BEEN_CALLED);
        assertThat(spyCandidateRepository.countHowManyTimesMethodAddBeenCalled()).isEqualTo(NUMBER_OF_TIMES_METHOD_SHOULD_BEEN_CALLED);
    }
}