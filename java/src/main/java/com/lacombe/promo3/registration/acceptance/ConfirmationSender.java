package com.lacombe.promo3.registration.acceptance;

import com.lacombe.promo3.EmailSender;
import com.lacombe.promo3.registration.CandidateConfirmationChecker;
import com.lacombe.promo3.registration.repository.DefaultConfirmationRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ConfirmationSender {
    public ConfirmationSender(DefaultConfirmationRepository defaultConfirmationRepository, CandidateConfirmationChecker candidateCofirmationChecker, EmailSender emailSender) {
    }

    public void execute() {
        throw new NotImplementedException();
    }
}
