package com.lacombe.promo3.registration.acceptance;

import com.lacombe.promo3.EmailSender;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.repository.ConfirmationRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ConfirmationSender {

    private ConfirmationRepository confirmationRepository;

    public ConfirmationSender(ConfirmationRepository confirmationRepository, CandidateConfirmationChecker candidateCofirmationChecker, EmailSender emailSender) {
    }

    public ConfirmationSender(ConfirmationRepository confirmationRepository) {
        this.confirmationRepository = confirmationRepository;
    }

    public void execute() {
        confirmationRepository.getEmails();
    }
}
