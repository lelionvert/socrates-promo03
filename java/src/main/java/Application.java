import com.lacombe.promo3.meals.*;
import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Application {

    private static final String CANDIDATE_ADDED_MESSAGE = "Le candidat a été ajouté.";
    private static final String NOT_VALID_EMAIL_MESSAGE = "L'email n'est pas valide.";
    private static final String CANDIDATE_EMAIL_MESSAGE = "Email du candidat :";
    private static final String NOT_FOUND_CANDIDATE_MESSAGE = "Aucun candidat trouvé.";
    private static final String ASK_FOR_CHECKIN_EMAIL_MESSAGE = "Ajouter une arrivee de participant\nVeuillez entrer l'email [e@mail.com] : \n";
    private static final String ASK_FOR_CHECKIN_DATE_MESSAGE = "Ajouter une arrivee de participant\nVeuillez entrer la date [JJ/MM/AAAA HH:mm:ss] : \n";
    private static final String CHECKIN_ADDED_MESSAGE = "L'arrivee de ce participant à été enregistrée.";
    private static final String NOT_VALID_DATE_MESSAGE = "La date entrée n'est pas valide.";

    private static CandidateRegistrationManager candidateRegistrationManager;
    private static final Scanner scanner = new Scanner(System.in);

    private static CheckInProvider checkInProvider;
    private static ColdMealsCounter coldMealsCounter;

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
            System.out.println("** ...                                        **");
            System.out.println("** 8 - Ajouter une arrivee de participant     **");
            System.out.println("** 9 - Compter le nombre de repas froids      **");
            System.out.println("** 0 - Quitter                                **");
            System.out.println("**                                            **");
            System.out.println("************************************************");

            choice = scanner.nextInt();
            scanner.nextLine(); // retrieve \n from the previous nextInt()

            switch (choice) {
                case 1 : showCandidatesEmail(); break;
                case 2 : addCandidate(); break;
                case 8 : addCheckIn(); break;
                case 9 : showCountColdMeals(); break;
            }

            System.out.println("Taper Entrer pour continuer ........");
            System.in.read();
        } while (choice != 0);

        System.exit(0);
    }

    private static void initManager() {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);
        checkInProvider = InMemoryCheckInProvider.of(new RegistrationBook(new ArrayList<CheckIn>()));
    }

    private static void addCandidate() throws IOException {
        System.out.println(CANDIDATE_EMAIL_MESSAGE);
        String emailValue = scanner.nextLine();
        Email email;
        try {
            email = Email.of(emailValue);
            Candidate candidate = new Candidate(email);
            candidateRegistrationManager.register(candidate);
            System.out.println(CANDIDATE_ADDED_MESSAGE);
        } catch (Exception e) {
            System.out.println(NOT_VALID_EMAIL_MESSAGE);
        }
    }

    private static void addCheckIn() throws IOException {
        Email email = null;
        LocalDateTime localDateTime = null;
        boolean invalidEmail = true;
        do {
            try {
                System.out.println(ASK_FOR_CHECKIN_EMAIL_MESSAGE);
                String emailValue = scanner.nextLine();
                email = Email.of(emailValue);
                invalidEmail = false;
            } catch (IllegalArgumentException e){
                    System.out.println(NOT_VALID_EMAIL_MESSAGE);
            }
        } while(invalidEmail);

        boolean invalidDate = true;
        do {
            try {
                System.out.println(ASK_FOR_CHECKIN_DATE_MESSAGE);
                String dateValue = scanner.nextLine();
                localDateTime = LocalDateTime.parse(dateValue, DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm[:ss]"));
                invalidDate = false;
            } catch (DateTimeParseException e) {
                System.out.println(NOT_VALID_DATE_MESSAGE);
            }
        } while(invalidDate);
        CheckIn checkIn = CheckIn.of(email, localDateTime);
        checkInProvider.add(checkIn);
        System.out.println(CHECKIN_ADDED_MESSAGE);
    }

    private static void showCountColdMeals() {
        coldMealsCounter = new ColdMealsCounter(checkInProvider);
        int count = coldMealsCounter.count();
        System.out.println("Nombre de repas froids : " + count);
    }


    private static void showCandidatesEmail() {
        Collection<Email> emails = candidateRegistrationManager.findEmails();
        if(emails.isEmpty()) {
            System.out.println(NOT_FOUND_CANDIDATE_MESSAGE);
        } else {
            emails.forEach(System.out::println);
        }
    }
}
