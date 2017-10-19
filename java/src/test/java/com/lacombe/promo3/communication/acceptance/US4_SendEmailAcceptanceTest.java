package com.lacombe.promo3.communication.acceptance;

import com.lacombe.promo3.communication.EmailMessage;
import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.EmailSenderDefault;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.communication.ConfirmationSender;
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
    private static final Candidate LUCAS_CANDIDATE = new Candidate(LUCAS_EMAIL, "cyril");
    private static final String JULIE_EMAIL_STRING = "julie@lcdlv.fr";
    private static final Email JULIE_EMAIL = Email.of(JULIE_EMAIL_STRING);
    private static final Candidate JULIE_CANDIDATE = new Candidate(JULIE_EMAIL, "cyril");

    @Ignore
    @Test
    public void should_send_a_confirmation_email_to_one_new_candidates() throws Exception {
        // GIVEN
        CandidateRepositoryInMemory defaultCandidateRepository =
                CandidateRepositoryInMemory.withExisting(LUCAS_CANDIDATE);
        ConfirmationRepositoryWriter confirmationRepository = new ConfirmationRepositoryInMemory();

        EmailSender emailSenderLogged = new EmailSenderDefault();

        CandidateConfirmationChecker candidateConfirmationChecker = new CandidateConfirmationChecker(defaultCandidateRepository);

        ConfirmationSender confirmationSender = new ConfirmationSender(confirmationRepository,
                                                                        candidateConfirmationChecker,
                                                                        emailSenderLogged);

        // WHEN
        confirmationSender.sendConfirmations();

        // THEN
        EmailMessage messageLucas = EmailMessage.of().withRecipient("houssam@lcdlv.fr")
                                    .withSender(LUCAS_EMAIL_STRING)
                                    .withObject("Confirmation")
                                    .withCore("Hello lucas, please confirm or pay.")
                                    .build();
        Assertions.assertThat(emailSenderLogged.getMessages())
                .isEqualTo(messageLucas);

        // TODO verifier log
        /*Assertions.assertThat(emailSenderLogged.printLog())
                .isEqualTo("Email sent to : " + LUCAS_CANDIDATE);*/
    }
}
