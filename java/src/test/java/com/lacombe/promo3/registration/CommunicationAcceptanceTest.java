package com.lacombe.promo3.registration;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CommunicationAcceptanceTest {



    @Test
    public void should_send_email_to_all_new_candidates() throws Exception {
        //given
        final Candidate sabineCandidate = new Candidate(Email.of("sabine@lcdlv.fr"));
        final DefaultCandidateRepository candidateRepository = DefaultCandidateRepository.withExisting(sabineCandidate);
        final EmailSender emailSender = new EmailSender();
        EmailService emailService = new EmailService(candidateRepository, emailSender);

        //when
        emailService.sendAskingConfirmationOrPayment();

        //then
        Assertions.assertThat(emailSender.print()).isEqualTo("PAYE SABINE");
    }
}
