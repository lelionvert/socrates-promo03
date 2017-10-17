package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderTest {

    private static final Email SABINE_EMAIL_ADDRESS = Email.of("sabine@lcdlv.fr");
    private static final Email MELODY_EMAIL_ADDRESS = Email.of("melody@lcdlv.fr");
    private static final String HOUSSAM_EMAIL_ADDRESS = "houssam@lcdlv.fr";

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    EmailSender emailSender;

    @Mock
    Logger logger;

    @Test
    public void should_send_email_to_one_candidate() {
        //GIVEN
        ConfirmationSender confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger);
        when(candidateRepository.getEmails()).thenReturn(Arrays.asList(SABINE_EMAIL_ADDRESS));

        //WHEN
        confirmationSender.send();

        final Message expectedMessage = Message.MessageBuilder.aMessage()
            .withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(SABINE_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        //THEN
        Mockito.verify(candidateRepository, times(1)).getEmails();
        Mockito.verify(emailSender, times(1)).send(expectedMessage);
        Mockito.verify(logger, times(1)).log(SABINE_EMAIL_ADDRESS);
    }

    @Test
    public void should_send_email_to_two_candidates() {
        //GIVEN
        ConfirmationSender confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger);
        when(candidateRepository.getEmails()).thenReturn(Arrays.asList(SABINE_EMAIL_ADDRESS, MELODY_EMAIL_ADDRESS));

        //WHEN
        confirmationSender.send();

        final Message expectedMessageForSabineCandidate = Message.MessageBuilder.aMessage()
            .withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(SABINE_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        final Message expectedMessageForMelodyCandidate = Message.MessageBuilder.aMessage()
            .withSender(HOUSSAM_EMAIL_ADDRESS)
            .withRecipient(MELODY_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        //THEN
        Mockito.verify(candidateRepository, times(1)).getEmails();
        Mockito.verify(emailSender, Mockito.atLeastOnce()).send(expectedMessageForSabineCandidate);
        Mockito.verify(emailSender, Mockito.atLeastOnce()).send(expectedMessageForMelodyCandidate);
        Mockito.verify(logger, Mockito.atLeastOnce()).log(SABINE_EMAIL_ADDRESS);
        Mockito.verify(logger, Mockito.atLeastOnce()).log(MELODY_EMAIL_ADDRESS);
    }

}