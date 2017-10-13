package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    public void should_find_list_of_candidates_emails() throws Exception {
        when(candidateRepository.getEmails()).thenReturn(Arrays.asList(Email.of("yohan@lcdlv.fr"), Email.of("sabine@lcdlv.fr")));
        EmailService emailService = new EmailService(candidateRepository, null);
        emailService.sendAskingConfirmationOrPayment();
        verify()
    }

}