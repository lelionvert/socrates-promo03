package com.lacombe.promo3.registration.unit;

import com.lacombe.promo3.registration.ConfirmationSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderUnitTest {

    @Mock
    private ConfirmationRepository confirmationRepository;

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
