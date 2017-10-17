package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.repository.CandidateRepository;

public class ConfirmationSender {

    private CandidateRepository candidateRepository;
    private EmailSender emailSender;
    private Logger logger;

    public ConfirmationSender(CandidateRepository candidateRepository, EmailSender emailSender, Logger logger) {
        this.candidateRepository = candidateRepository;
        this.emailSender = emailSender;
        this.logger = logger;
    }

    public void send() {

        for (Candidate candidate : candidateRepository.getCandidates()) {

            emailSender.send(MessageTemplate.createMessage(candidate));

            logger.log(candidate.getEmail());
        }
    }

}
