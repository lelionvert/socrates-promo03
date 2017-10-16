package com.lacombe.promo3.registration.acceptance;

import com.lacombe.promo3.EmailSender;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.ConfirmationSender;
import com.lacombe.promo3.registration.EmailSenderWithLogger;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.ConfirmationRepository;
import com.lacombe.promo3.registration.repository.CandidateRepositoryDefault;
import com.lacombe.promo3.registration.repository.ConfirmationRepositoryDefault;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class US4_SendEmailAcceptanceTest {

    private static final Email LUCAS_EMAIL = Email.of("lucas@lcdlv.fr");
    private static final Candidate LUCAS_CANDIDATE = new Candidate(LUCAS_EMAIL);
    private static final Email JULIE_EMAIL = Email.of("julie@lcdlv.fr");
    private static final Candidate JULIE_CANDIDATE = new Candidate(JULIE_EMAIL);

    @Ignore
    @Test
    public void should_send_a_confirmation_or_payment_email_to_all_new_candidates() throws Exception {
        // GIVEN
        CandidateRepositoryDefault defaultCandidateRepository =
                CandidateRepositoryDefault.withExisting(LUCAS_CANDIDATE, JULIE_CANDIDATE);
        ConfirmationRepository confirmationRepository = new ConfirmationRepositoryDefault();

        EmailSender emailSender = new EmailSenderWithLogger();

        CandidateConfirmationChecker candidateConfirmationChecker = new CandidateConfirmationChecker(defaultCandidateRepository);

        ConfirmationSender confirmationSender = new ConfirmationSender(confirmationRepository,
                                                                        candidateConfirmationChecker,
                                                                        emailSender);

        // WHEN
        confirmationSender.execute();

        // THEN
        Assertions.assertThat(((EmailSenderWithLogger)emailSender).printLog())
                .isEqualTo("Email sent to : " + LUCAS_CANDIDATE + "\n"
                + "Email sent to : " + JULIE_CANDIDATE);
    }
}
