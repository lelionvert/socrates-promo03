package com.lacombe.promo3.communication.unit;

import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepositoryWriter;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.ConfirmationSender;
import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderUnitTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL, "cyril");

    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");
    private static final Candidate CYRIL_CANDIDATE = new Candidate(CYRIL_EMAIL, "cyril");

    @Mock
    private EmailSender emailSender;

    @Mock
    private CandidateConfirmationChecker candidateConfirmationChecker;

    @Mock
    private ConfirmationRepositoryWriter confirmationRepositoryWriter;
    private ConfirmationSender confirmationSender;

    @Before
    public void setUp() throws Exception {
        confirmationSender = new ConfirmationSender(confirmationRepositoryWriter
                , candidateConfirmationChecker
                , emailSender);

    }

    @Test
    public void should_send_none_confirmation_email_when_no_candidates_registered() throws Exception {
        List<Candidate> candidates = Collections.emptyList();
        when(candidateConfirmationChecker.getCandidates()).thenReturn(candidates);
        when(emailSender.sendToMany(eq(candidates))).thenReturn(
                new EmailsStatus(Collections.emptyList())
        );

        confirmationSender.execute();

        verify(candidateConfirmationChecker, times(1)).getCandidates();
        verify(emailSender, times(0)).sendToMany(any(Collection.class));
        verify(confirmationRepositoryWriter, times(0)).add(any(Email.class));

    }

    @Test
    public void should_send_one_confirmation_email_when_no_confirmations_emails_are_sent() throws Exception {
        List<Candidate> candidates = asList(SABINE_CANDIDATE);
        when(candidateConfirmationChecker.getCandidates()).thenReturn(candidates);
        when(emailSender.sendToMany(eq(candidates))).thenReturn(
                new EmailsStatus(asList(SABINE_EMAIL))
        );

        confirmationSender.execute();

        verify(candidateConfirmationChecker, times(1)).getCandidates();
        verify(emailSender, times(1)).sendToMany(any(Collection.class));
        verify(confirmationRepositoryWriter, times(1)).add(any(Email.class));
    }

    @Test
    public void should_send_many_confirmations_emails_when_no_confirmations_emails_are_sent() throws Exception {
        List<Candidate> candidates = asList(SABINE_CANDIDATE, CYRIL_CANDIDATE);
        when(candidateConfirmationChecker.getCandidates()).thenReturn(candidates);
        when(emailSender.sendToMany(eq(candidates))).thenReturn(
                new EmailsStatus(asList(SABINE_EMAIL, CYRIL_EMAIL))
        );

        confirmationSender.execute();

        verify(candidateConfirmationChecker, times(1)).getCandidates();
        verify(emailSender, times(1)).sendToMany(any(Collection.class));
        verify(confirmationRepositoryWriter, times(2)).add(any(Email.class));
    }


}