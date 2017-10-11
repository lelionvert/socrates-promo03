package com.lacombe.promo3.registration.acceptance;

import com.lacombe.promo3.registration.EmailLogger;
import com.lacombe.promo3.registration.EmailService;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class US4_SendEmailAcceptanceTest {

    public static final Email LUCAS_EMAIL = Email.of("lucas@lcdlv.fr");
    public static final Candidate LUCAS_CANDIDATE = new Candidate(LUCAS_EMAIL);
    public static final Email JULIE_EMAIL = Email.of("julie@lcdlv.fr");
    public static final Candidate JULIE_CANDIDATE = new Candidate(JULIE_EMAIL);

    @Test
    public void should_send_a_confirmation_or_payment_email_to_all_candidates() throws Exception {
        // GIVEN
        DefaultCandidateRepository defaultCandidateRepository =
                DefaultCandidateRepository.withExisting(LUCAS_CANDIDATE, JULIE_CANDIDATE);
        EmailLogger emailLogger = new EmailLogger();
        EmailService emailService = new EmailService(defaultCandidateRepository, emailLogger);

        // WHEN
        emailService.sendConfirmationOrPaymentEmail();

        // THEN
        Assertions.assertThat(emailLogger.print())
                .isEqualTo("Email sent to :" + LUCAS_CANDIDATE
                                             + ", " + JULIE_CANDIDATE);
    }
}
