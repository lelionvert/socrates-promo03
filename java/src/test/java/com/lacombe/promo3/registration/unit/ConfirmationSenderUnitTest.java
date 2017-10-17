package com.lacombe.promo3.registration.unit;

import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepository;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.ConfirmationSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderUnitTest {

    @Mock
    private EmailSender emailSender;

    @Mock
    private CandidateConfirmationChecker candidateConfirmationChecker;

    @Mock
    private ConfirmationRepository confirmationRepository;

    @Test
    public void should_get_candidate_list_from_checker() throws Exception {
        //ARRANGE
        ConfirmationSender confirmationSender = new ConfirmationSender(emailSender, candidateConfirmationChecker);
        //ACT
        confirmationSender.execute();
        //ASSERT
        verify(candidateConfirmationChecker, times(1)).getCandidates();
    }

    @Test
    public void should_send_an_email_from_email_sender() throws Exception {
        // ARRANGE
        ConfirmationSender confirmationSender = new ConfirmationSender(emailSender
                                                                        , candidateConfirmationChecker);
        // ACT
        confirmationSender.execute();
        // ASSERT
        verify(emailSender, times(1)).send();
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
