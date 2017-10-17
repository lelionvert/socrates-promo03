package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;

import java.util.Collection;

import static com.lacombe.promo3.communication.Message.*;

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
        final Collection<Email> emails = candidateRepository.getEmails();
        for (Email email : emails) {
            final Message message = MessageBuilder.aMessage()
                .withSender("houssam@lcdlv.fr")
                .withRecipient(email)
                .withBody("Hello Sabine,\n Can you confirm me that you are coming at Socrates?\n Regards,\n Houssam Fakih")
                .withObject("Confirmation").build();
            emailSender.send(message);
            logger.log(email);
        }
    }
}
