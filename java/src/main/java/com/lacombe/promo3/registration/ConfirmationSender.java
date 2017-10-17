package com.lacombe.promo3.registration;

import com.lacombe.promo3.communication.EmailSender;
import com.lacombe.promo3.communication.repository.ConfirmationRepository;

public class ConfirmationSender {

    private ConfirmationRepository confirmationRepository;

    public ConfirmationSender(ConfirmationRepository confirmationRepository,
                              CandidateConfirmationChecker candidateCofirmationChecker, EmailSender emailSender) {
    }

    public ConfirmationSender(ConfirmationRepository confirmationRepository) {
        this.confirmationRepository = confirmationRepository;
    }

    public void execute() {
        confirmationRepository.getEmails();
    }
}
