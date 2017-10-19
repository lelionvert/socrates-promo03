package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfirmationSender {

    private CandidateRepository candidateRepository;
    private EmailSender emailSender;
    private Logger logger;
    private EmailArchiver emailArchiver;

    public ConfirmationSender(CandidateRepository candidateRepository, EmailSender emailSender, Logger logger, EmailArchiver EmailArchiver) {
        this.candidateRepository = candidateRepository;
        this.emailSender = emailSender;
        this.logger = logger;
        this.emailArchiver = EmailArchiver;
    }

    public void send() {

        for (Candidate candidate : getCandidatesThatDidNotReceiveTheConfirmationMessage()) {

            emailSender.send(MessageTemplate.createMessage(candidate));

            logger.log(candidate.getEmail());

            emailArchiver.add(candidate.getEmail());
        }
    }

    private List<Candidate> getCandidatesThatDidNotReceiveTheConfirmationMessage() {
        Collection<Email> emailsAlreadyUsed = emailArchiver.retrieveEmails();
        List<Candidate> candidates = new ArrayList<>(candidateRepository.getCandidates());

        final Predicate<Candidate> confirmationNotSentPredicate = candidate -> !emailsAlreadyUsed.contains(candidate.getEmail());
        return candidates.stream().filter(confirmationNotSentPredicate).collect(Collectors.toList());

    }

}
