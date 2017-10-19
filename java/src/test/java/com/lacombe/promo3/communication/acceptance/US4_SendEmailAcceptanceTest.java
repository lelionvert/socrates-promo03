package com.lacombe.promo3.communication.acceptance;

import com.lacombe.promo3.communication.EmailMessage;
import com.lacombe.promo3.communication.EmailSenderLoggedInConsole;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.ConfirmationSender;
import com.lacombe.promo3.communication.EmailSenderLogged;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;
import com.lacombe.promo3.communication.repository.ConfirmationRepositoryWriter;
import com.lacombe.promo3.shared.repository.CandidateRepositoryInMemory;
import com.lacombe.promo3.communication.repository.ConfirmationRepositoryInMemory;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class US4_SendEmailAcceptanceTest {

    private static final String LUCAS_EMAIL_STRING = "lucas@lcdlv.fr";
    private static final Email LUCAS_EMAIL = Email.of(LUCAS_EMAIL_STRING);
    private static final Candidate LUCAS_CANDIDATE = new Candidate(LUCAS_EMAIL);
    private static final String JULIE_EMAIL_STRING = "julie@lcdlv.fr";
    private static final Email JULIE_EMAIL = Email.of(JULIE_EMAIL_STRING);
    private static final Candidate JULIE_CANDIDATE = new Candidate(JULIE_EMAIL);

    @Ignore
    @Test
    public void should_send_a_confirmation_email_to_one_new_candidates() throws Exception {
        // GIVEN
        CandidateRepositoryInMemory defaultCandidateRepository =
                CandidateRepositoryInMemory.withExisting(LUCAS_CANDIDATE);
        ConfirmationRepositoryWriter confirmationRepository = new ConfirmationRepositoryInMemory();

        EmailSenderLogged emailSenderLogged = new EmailSenderLoggedInConsole();

        CandidateConfirmationChecker candidateConfirmationChecker = new CandidateConfirmationChecker(defaultCandidateRepository);

        ConfirmationSender confirmationSender = new ConfirmationSender(confirmationRepository,
                                                                        candidateConfirmationChecker,
                                                                        emailSenderLogged);

        // WHEN
        confirmationSender.execute();

        // THEN
        EmailMessage messageLucas = EmailMessage.of().withRecipient("houssam@lcdlv.fr")
                                    .withSender(LUCAS_EMAIL_STRING)
                                    .withObject("Confirmation")
                                    .withCore("Hello, please confirm or pay.")
                                    .build();
        Assertions.assertThat(emailSenderLogged.getMessage())
                .isEqualTo(messageLucas);
        Assertions.assertThat(emailSenderLogged.printLog())
                .isEqualTo("Email sent to : " + LUCAS_CANDIDATE);
    }
}
