package com.lacombe.promo3.registration;

import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepository;

public class ConfirmationSender {

    private ConfirmationRepository confirmationRepository;
    private CandidateConfirmationChecker candidateConfirmationChecker;

    public ConfirmationSender(ConfirmationRepository confirmationRepository,
                              CandidateConfirmationChecker candidateConfirmationChecker, EmailSender emailSender) {
        this.confirmationRepository = confirmationRepository;
        this.candidateConfirmationChecker = candidateConfirmationChecker;
    }

    public ConfirmationSender(CandidateConfirmationChecker candidateConfirmationChecker) {
        this.candidateConfirmationChecker = candidateConfirmationChecker;
    }

    public void execute() {
        candidateConfirmationChecker.getCandidates();
    }
}
