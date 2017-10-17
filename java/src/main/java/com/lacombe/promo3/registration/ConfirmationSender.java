package com.lacombe.promo3.registration;

import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepositoryWriter;
import com.lacombe.promo3.shared.model.Candidate;
import com.lacombe.promo3.shared.model.Email;

import java.util.Collection;

public class ConfirmationSender {

    private ConfirmationRepositoryWriter confirmationRepositoryWriter;
    private CandidateConfirmationChecker candidateConfirmationChecker;
    private EmailSender emailSender;

    public ConfirmationSender(ConfirmationRepositoryWriter confirmationRepositoryWriter
                            , CandidateConfirmationChecker candidateConfirmationChecker
                            , EmailSender emailSender) {
        this.confirmationRepositoryWriter = confirmationRepositoryWriter;
        this.candidateConfirmationChecker = candidateConfirmationChecker;
        this.emailSender = emailSender;
    }

    public ConfirmationSender(EmailSender emailSender, CandidateConfirmationChecker candidateConfirmationChecker) {
        this.emailSender = emailSender;
        this.candidateConfirmationChecker = candidateConfirmationChecker;
    }

    public void execute() {
        Collection<Candidate> candidates = candidateConfirmationChecker.getCandidates();
        EmailsStatus emailsStatus = emailSender.sendTo(candidates);
        saveConfirmationSent(emailsStatus);
    }

    private void saveConfirmationSent(EmailsStatus emailsStatus) {
        for(Email email : emailsStatus.getEmailsSent()) {
            confirmationRepositoryWriter.add(email);
        }
    }
}
