package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderTest {

    public static final Email SABINE_EMAIL_ADDRESS = Email.of("sabine@lcdlv.fr");
    public static final Email MELODY_EMAIL_ADDRESS = Email.of("melody@lcdlv.fr");
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
            .withSender("houssam@lcdlv.fr")
            .withRecipient(SABINE_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello Sabine,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
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
            .withSender("houssam@lcdlv.fr")
            .withRecipient(SABINE_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello Sabine,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        final Message expectedMessageForMelodyCandidate = Message.MessageBuilder.aMessage()
            .withSender("houssam@lcdlv.fr")
            .withRecipient(MELODY_EMAIL_ADDRESS)
            .withObject("Confirmation")
            .withBody("Hello Melody,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
            .build();

        //THEN
        Mockito.verify(candidateRepository, times(1)).getEmails();
        Mockito.verify(emailSender, times(1)).send(expectedMessageForSabineCandidate);
        Mockito.verify(logger, times(1)).log(SABINE_EMAIL_ADDRESS);
    }

}