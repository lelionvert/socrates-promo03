import com.lacombe.promo3.communication.ConfirmationSender;
import com.lacombe.promo3.communication.EmailStatus;
import com.lacombe.promo3.communication.repository.DefaultEmailArchiver;
import com.lacombe.promo3.communication.repository.DefaultLogger;
import com.lacombe.promo3.communication.repository.EmailSender;
import com.lacombe.promo3.communication.repository.SMTPEmailSender;
import com.lacombe.promo3.meals.*;
import com.lacombe.promo3.registration.CandidateRegister;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

class Application {

    public static final String NO_EMAIL_SENT_MESSAGE = "Aucun email n'a été envoyé.";
    private final PrintStream out;
    private final Scanner in;
    private final String CANDIDATE_FIRST_NAME = "Prénom du candidat :";
    private final String CONFIRMATIONS_EMAIL_ARE_SENT = "Les emails de confirmation ont été envoyés.";
    private static final String CANDIDATE_ADDED_MESSAGE = "Le candidat a été ajouté.";
    private static final String NOT_VALID_EMAIL_MESSAGE = "L'email n'est pas valide.";
    private static final String CANDIDATE_EMAIL_MESSAGE = "Email du candidat :";
    private static final String NOT_FOUND_CANDIDATE_MESSAGE = "Aucun candidat trouvé.";
    private static final String ASK_FOR_CHECKIN_EMAIL_MESSAGE = "Ajouter une arrivee de participant\nVeuillez entrer l'email [e@mail.com] : \n";
    private static final String ASK_FOR_CHECKIN_DATE_MESSAGE = "Ajouter une arrivee de participant\nVeuillez entrer la date [JJ/MM/AAAA HH:mm:ss] : \n";
    private static final String CHECKIN_ADDED_MESSAGE = "L'arrivee de ce participant à été enregistrée.";
    private static final String NOT_VALID_DATE_MESSAGE = "La date entrée n'est pas valide.";

    private final String NO_CONFIRMATIONS_EMAIL_SENT = "A jour. Aucun mails de confirmation à envoyer.";
    private CandidateRegister candidateRegister;
    private ConfirmationSender confirmationSender;
    private final InputStream in1;
    private final DefaultLogger defaultLogger = new DefaultLogger();

    private static CheckInProvider checkInProvider;
    private static ColdMealsCounter coldMealsCounter;

    public static void main(String[] args) throws IOException {
        Application application = new Application(System.out, System.in);
        application.mainBlock();
    }

    Application(PrintStream out, InputStream in2) {
        this.out = out;
        this.in1 = in2;
        this.in = new Scanner(in1);
    }

    void mainBlock() throws IOException {
        init();

        int choice;
        do {
            out.println("************************************************");
            out.println("**              SOCRATES FR                   **");
            out.println("************************************************");
            out.println("**                                            **");
            out.println("** 1 - Récupérer la liste des emails candidat **");
            out.println("** 2 - Ajouter un candidat                    **");
            out.println("** 3 - Envoyer les emails de confirmation     **");
            out.println("** 4 - Afficher les logs d'envoi d'emails     **");
            out.println("** 5 - Ajouter une arrivee de participant     **");
            out.println("** 6 - Compter le nombre de repas froids      **");
            out.println("** 0 - Quitter                                **");
            out.println("**                                            **");
            out.println("************************************************");

            choice = in.nextInt();
            in.nextLine(); // retrieve \n from the previous nextInt()

            switch (choice) {
                case 1:
                    showCandidatesEmail();
                    break;
                case 2:
                    addCandidate();
                    break;
                case 3:
                    sendConfirmations();
                    break;
                case 4:
                    showEmailConfirmationLog();
                    break;
                case 5 :
                    addCheckIn();
                    break;
                case 6 :
                    showCountColdMeals();
                    break;
            }

            out.println("Taper Entrer pour continuer ........\n");
            in1.read();
        } while (choice != 0);
    }

    private void showEmailConfirmationLog() {
        String log = defaultLogger.print();
        if(log.isEmpty()) {
            log = NO_EMAIL_SENT_MESSAGE;
        }
        out.println(log);
    }

    private void sendConfirmations() {
        String message;
        if (confirmationSender.send() == EmailStatus.NO_EMAIL_SENT) {
            message = NO_CONFIRMATIONS_EMAIL_SENT;
        } else {
            message = CONFIRMATIONS_EMAIL_ARE_SENT;
        }
        out.println(message);
    }

    private void init() {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();

        candidateRegister = new CandidateRegister(defaultCandidateRepository);
        checkInProvider = InMemoryCheckInProvider.of(new RegistrationBook(new ArrayList<CheckIn>()));

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        EmailSender emailSender = new SMTPEmailSender(properties);
        DefaultEmailArchiver defaultArchiveEmail = new DefaultEmailArchiver();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, emailSender, defaultLogger, defaultArchiveEmail);
    }

    private void addCandidate() {
        out.println(CANDIDATE_FIRST_NAME);
        String firstNameValue = in.nextLine();
        String CANDIDATE_EMAIL_MESSAGE = "Email du candidat :";
        out.println(CANDIDATE_EMAIL_MESSAGE);
        String emailValue = in.nextLine();
        Email email;
        boolean isAdded = false;
        try {
            email = Email.of(emailValue);
            Candidate candidate = new Candidate(email, firstNameValue);
            isAdded = candidateRegister.register(candidate);
        } catch (Exception e) {
            String NOT_VALID_EMAIL_MESSAGE = "L'email n'est pas valide.";
            out.println(NOT_VALID_EMAIL_MESSAGE);
        }
        String message;
        String CANDIDATE_ADDED_MESSAGE = "Le candidat a été ajouté.";
        if (isAdded)
            message = CANDIDATE_ADDED_MESSAGE;
        else
            message = "Il existe déjà un candidat avec cet email.";
        out.println(message);

    }

    private void addCheckIn() throws IOException {
        Email email = null;
        LocalDateTime localDateTime = null;
        boolean invalidEmail = true;
        do {
            try {
                System.out.println(ASK_FOR_CHECKIN_EMAIL_MESSAGE);
                String emailValue = this.in.nextLine();
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
                String dateValue = this.in.nextLine();
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


    private void showCandidatesEmail() {
        Collection<Email> emails = candidateRegister.findEmails();
        if (emails.isEmpty()) {
            out.println(NOT_FOUND_CANDIDATE_MESSAGE);
        } else {
            emails.forEach(out::println);
        }
    }
}
