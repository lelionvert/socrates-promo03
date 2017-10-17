package com.lacombe.promo3.registration.unit;

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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderUnitTest {

    private static final Email SABINE_EMAIL = Email.of("sabine@lcdlv.fr");
    private static final Email CYRIL_EMAIL = Email.of("cyril@lcdlv.fr");

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

        when(candidateConfirmationChecker.getCandidates()).thenReturn(Collections.emptyList());
    }

    @Test
    public void should_send_one_confirmation_email_when_no_confirmations_emails_are_sent() throws Exception {
        when(emailSender.sendTo(Matchers.any())).thenReturn(
                new EmailsStatus(
                        new ArrayList<Email>() {{
                            add(SABINE_EMAIL);
                        }})
        );

        confirmationSender.execute();

        verify(candidateConfirmationChecker, times(1)).getCandidates();
        verify(emailSender, times(1)).sendTo(any(Collection.class));
        verify(confirmationRepositoryWriter, times(1)).add(any(Email.class));
    }

    @Test
    public void should_send_many_confirmations_emails_when_no_confirmations_emails_are_sent() throws Exception {
        when(emailSender.sendTo(Matchers.any())).thenReturn(
                new EmailsStatus(
                        new ArrayList<Email>() {{
                            add(SABINE_EMAIL);
                            add(CYRIL_EMAIL);
                        }})
        );

        confirmationSender.execute();

        verify(candidateConfirmationChecker, times(1)).getCandidates();
        verify(emailSender, times(1)).sendTo(any(Collection.class));
        verify(confirmationRepositoryWriter, times(2)).add(any(Email.class));
    }

    /* TODO Dans le checker */
    /*
    @Ignore
    @Test
    public void should_call_get_confirmations_email_list() throws Exception {
        //ARRANGE
        ConfirmationSender confirmationSender = new ConfirmationSender(confirmationRepository, candidateConfirmationChecker);
        //ACT
        confirmationSender.execute();
        //ASSERT
        verify(confirmationRepository, times(1)).getEmails();
    }
    */
}
