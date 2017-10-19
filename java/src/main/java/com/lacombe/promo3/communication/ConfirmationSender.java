package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfirmationSender {

    private CandidateRepository candidateRepository;
    private EmailSender emailSender;
    private Logger logger;
    private ArchiveEmail archiveEmail;

    public ConfirmationSender(CandidateRepository candidateRepository, EmailSender emailSender, Logger logger, ArchiveEmail archiveEmail) {
        this.candidateRepository = candidateRepository;
        this.emailSender = emailSender;
        this.logger = logger;
        this.archiveEmail = archiveEmail;
    }

    public void send() {

        for (Candidate candidate : getCandidatesThatDidNotReceiveTheConfirmationMessage()) {

            emailSender.send(MessageTemplate.createMessage(candidate));

            logger.log(candidate.getEmail());

            archiveEmail.add(candidate.getEmail());
        }
    }

    private List<Candidate> getCandidatesThatDidNotReceiveTheConfirmationMessage() {
        Collection<Email> emailsAlreadyUsed = getEmailsAlreadyUsedForConfirmationEmail();
        List<Candidate> candidates = new ArrayList<>(candidateRepository.getCandidates());

        for(Candidate candidate : candidates) {
            if(emailsAlreadyUsed.contains(candidate.getEmail())) {
                candidates.remove(candidate);
            }
        }
        return candidates;
    }

    public Collection<Email> getEmailsAlreadyUsedForConfirmationEmail() {
        return archiveEmail.retrieveEmails();
    }

}
