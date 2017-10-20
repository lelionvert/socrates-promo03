package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.model.Emails;
import com.lacombe.promo3.communication.model.MessageTemplate;
import com.lacombe.promo3.communication.repository.EmailArchiver;
import com.lacombe.promo3.communication.repository.EmailSender;
import com.lacombe.promo3.communication.repository.Logger;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfirmationSender {

    private final CandidateRepository candidateRepository;
    private final EmailSender emailSender;
    private final Logger logger;
    private final EmailArchiver emailArchiver;

    public ConfirmationSender(CandidateRepository candidateRepository, EmailSender emailSender, Logger logger, EmailArchiver EmailArchiver) {
        this.candidateRepository = candidateRepository;
        this.emailSender = emailSender;
        this.logger = logger;
        this.emailArchiver = EmailArchiver;
    }

    public EmailStatus send() {

        final List<Candidate> candidates = getCandidatesThatDidNotReceiveTheConfirmationMessage();

        if(candidates.isEmpty())
            return EmailStatus.NO_EMAIL_SENT;

        for (Candidate candidate : candidates) {

            final Email email = candidate.getEmail();

            emailSender.send(MessageTemplate.createMessage(candidate));

            logger.log(email);

            emailArchiver.add(email);
        }
        return EmailStatus.ALL_EMAIL_SENT;
    }

    private List<Candidate> getCandidatesThatDidNotReceiveTheConfirmationMessage() {
        Emails emailsArchived = emailArchiver.retrieveEmails();
        List<Candidate> candidates = new ArrayList<>(candidateRepository.getCandidates());

        final Predicate<Candidate> confirmationNotSentPredicate = candidate -> !candidate.isContainedIn(emailsArchived);
        return candidates.stream().filter(confirmationNotSentPredicate).collect(Collectors.toList());

    }

}
