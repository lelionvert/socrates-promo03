package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.model.Emails;
import com.lacombe.promo3.communication.model.MessageTemplate;
import com.lacombe.promo3.communication.repository.EmailArchiver;
import com.lacombe.promo3.communication.repository.EmailSender;
import com.lacombe.promo3.communication.repository.Logger;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ConfirmationSenderTest {

    private static final Email SABINE_EMAIL_ADDRESS = Email.of("sabine@lcdlv.fr");
    private static final Candidate SABINE_CANDIDATE = new Candidate(SABINE_EMAIL_ADDRESS, "Sabine");
    private static final Email MELODY_EMAIL_ADDRESS = Email.of("melody@lcdlv.fr");
    private static final Candidate MELODY_CANDIDATE = new Candidate(MELODY_EMAIL_ADDRESS, "Melody");

    private ConfirmationSender confirmationSender;

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    EmailSender emailSender;

    @Mock
    Logger logger;

    @Mock
    EmailArchiver emailArchiver;

    @Test
    public void should_not_send_any_email() throws Exception {
        //GIVEN
        confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger, emailArchiver);
        when(candidateRepository.getCandidates()).thenReturn(Collections.EMPTY_LIST);

        //WHEN
        confirmationSender.send();

        //THEN
        Assertions.assertThat(emailArchiver.retrieveEmails()).isNull();
    }

    @Test
    public void should_send_email_to_one_candidate() {
        //GIVEN
        confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger, emailArchiver);
        when(candidateRepository.getCandidates()).thenReturn(Arrays.asList(SABINE_CANDIDATE));
        when(emailArchiver.retrieveEmails()).thenReturn(new Emails());

        //WHEN
        confirmationSender.send();

        //THEN
        Mockito.verify(candidateRepository, times(1)).getCandidates();
        Mockito.verify(emailSender, times(1)).send(MessageTemplate.createMessage(SABINE_CANDIDATE));
        Mockito.verify(logger, times(1)).log(SABINE_EMAIL_ADDRESS);
    }

    @Test
    public void should_send_email_to_two_candidates() {
        //GIVEN
        confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger, emailArchiver);
        when(candidateRepository.getCandidates()).thenReturn(Arrays.asList(SABINE_CANDIDATE, MELODY_CANDIDATE));
        when(emailArchiver.retrieveEmails()).thenReturn(new Emails());
        //WHEN
        confirmationSender.send();

        //THEN
        Mockito.verify(candidateRepository, times(1)).getCandidates();
        Mockito.verify(emailSender, Mockito.atLeastOnce()).send(MessageTemplate.createMessage(SABINE_CANDIDATE));
        Mockito.verify(emailSender, Mockito.atLeastOnce()).send(MessageTemplate.createMessage(MELODY_CANDIDATE));
        Mockito.verify(logger, Mockito.atLeastOnce()).log(SABINE_EMAIL_ADDRESS);
        Mockito.verify(logger, Mockito.atLeastOnce()).log(MELODY_EMAIL_ADDRESS);
    }

    @Test
    public void should_send_email_to_only_one_candidate_form_a_list_of_two_candidates() {
        //GIVEN
        confirmationSender = new ConfirmationSender(candidateRepository, emailSender, logger, emailArchiver);
        when(candidateRepository.getCandidates()).thenReturn(Arrays.asList(SABINE_CANDIDATE,MELODY_CANDIDATE));
        when(emailArchiver.retrieveEmails()).thenReturn(Emails.with(SABINE_EMAIL_ADDRESS));


        //WHEN
        confirmationSender.send();
        when(emailArchiver.retrieveEmails()).thenReturn(Emails.with(SABINE_EMAIL_ADDRESS, MELODY_EMAIL_ADDRESS));

        //THEN
        Mockito.verify(candidateRepository, times(1)).getCandidates();
        Mockito.verify(emailSender, times(1)).send(MessageTemplate.createMessage(MELODY_CANDIDATE));
        Mockito.verify(logger, times(1)).log(MELODY_EMAIL_ADDRESS);
        Mockito.verify(emailSender, never()).send(MessageTemplate.createMessage(SABINE_CANDIDATE));
        Mockito.verify(logger, never()).log(SABINE_EMAIL_ADDRESS);

        Assertions.assertThat(emailArchiver.retrieveEmails()).isEqualTo(Emails.with(SABINE_EMAIL_ADDRESS, MELODY_EMAIL_ADDRESS));
    }

}