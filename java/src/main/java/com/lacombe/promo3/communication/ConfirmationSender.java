package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.repository.ConfirmationRepositoryWriter;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.EmailsStatus;
import com.lacombe.promo3.shared.model.Candidates;
import com.lacombe.promo3.shared.model.Email;

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

    public void sendConfirmations() {
        Candidates candidates = retrieveCandidateWaitingForConfirmation();
        EmailsStatus emailsStatus = sendEmails(candidates);
        saveConfirmations(emailsStatus);
    }

    private EmailsStatus sendEmails(Candidates candidates) {
        if(candidates.isEmpty())
            return new EmailsStatus();
        return emailSender.sendToMany(candidates);
    }

    private Candidates retrieveCandidateWaitingForConfirmation() {
        return candidateConfirmationChecker.getCandidates();
    }

    private void saveConfirmations(EmailsStatus emailsStatus) {
        if(emailsStatus.isEmpty()){
                return;
        }
        for(Email email : emailsStatus.getEmailsSent()) {
            confirmationRepositoryWriter.add(email);
        }
    }
}
