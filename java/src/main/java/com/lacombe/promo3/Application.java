package com.lacombe.promo3;

import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.CandidateRepository;
import com.lacombe.promo3.registration.repository.DBCandidateRepository;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;


public class Application {

    private static final String CANDIDATE_ADDED_MESSAGE = "Le candidat a été ajouté.";
    private static final String NOT_VALID_EMAIL_MESSAGE = "L'email n'est pas valide.";
    private static final String CANDIDATE_EMAIL_MESSAGE = "Email du candidat :";
    private static final String NO_FOUND_CANDIDATE_MESSAGE = "Aucun candidat trouvé.";

    private static CandidateRegistrationManager candidateRegistrationManager;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        initManager();

        int choice;
        do {
            System.out.println("************************************************");
            System.out.println("**              SOCRATES FR                   **");
            System.out.println("************************************************");
            System.out.println("**                                            **");
            System.out.println("** 1 - Récupérer la liste des emails candidat **");
            System.out.println("** 2 - Ajouter un candidat                    **");
            System.out.println("** 0 - Quitter                                **");
            System.out.println("**                                            **");
            System.out.println("************************************************");

            choice = scanner.nextInt();
            scanner.nextLine(); // retrieve \n from the previous nextInt()

            switch (choice) {
                case 1 : showCandidatesEmail(); break;
                case 2 : addCandidate(); break;
            }

            System.out.println("Taper Entrer pour continuer ........");
            System.in.read();
        } while (choice != 0);

        System.exit(0);
    }

    private static void initManager() {
        //CandidateRepository candidateRepository = new DefaultCandidateRepository();
        CandidateRepository candidateRepository = new DBCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(candidateRepository);
    }

    private static void addCandidate() throws IOException {
        System.out.println(CANDIDATE_EMAIL_MESSAGE);
        String emailValue = scanner.nextLine();
        Email email;

            email = Email.of(emailValue);
            Candidate candidate = new Candidate(email);
            candidateRegistrationManager.register(candidate);
            System.out.println(CANDIDATE_ADDED_MESSAGE);
        try {
        } catch (Exception e) {
            System.out.println(e+ "\n" + NOT_VALID_EMAIL_MESSAGE);
        }
    }

    private static void showCandidatesEmail() {
        Collection<Email> emails = candidateRegistrationManager.findEmails();
        if(emails.isEmpty()) {
            System.out.println(NO_FOUND_CANDIDATE_MESSAGE);
        } else {
            emails.forEach(System.out::println);
        }
    }
}