package com.lacombe.promo3.registration.unit;

import com.lacombe.promo3.EmailSender;
import com.lacombe.promo3.registration.acceptance.ConfirmationSender;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.ConfirmationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderUnitTest {

    @Mock
    EmailSender emailSender;

    @Mock
    ConfirmationRepository confirmationRepository;

    @Test
    public void should_get_confirmations_email_list() throws Exception {
        //ARRANGE
        ConfirmationSender confirmationSender = new ConfirmationSender(confirmationRepository);
        //ACT
        confirmationSender.execute();
        //ASSERT
        verify(confirmationRepository, times(1)).getEmails();
    }
}
