import com.lacombe.promo3.communication.ConfirmationSender;
import com.lacombe.promo3.communication.EmailStatus;
import com.lacombe.promo3.communication.repository.DefaultEmailArchiver;
import com.lacombe.promo3.communication.repository.DefaultLogger;
import com.lacombe.promo3.communication.repository.EmailSender;
import com.lacombe.promo3.communication.repository.SMTPEmailSender;
import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class Application {

    private static final String CANDIDATE_ADDED_MESSAGE = "Le candidat a été ajouté.";
    private static final String NOT_VALID_EMAIL_MESSAGE = "L'email n'est pas valide.";
    private static final String CANDIDATE_EMAIL_MESSAGE = "Email du candidat :";
    private static final String NO_FOUND_CANDIDATE_MESSAGE = "Aucun candidat trouvé.";
    public static final String CANDIDATE_FIRST_NAME = "Prénom du candidat :";

    private static CandidateRegistrationManager candidateRegistrationManager;
    private static final Scanner scanner = new Scanner(System.in);
    private static ConfirmationSender confirmationSender;

    public static void main(String[] args) throws IOException {
        init();

        int choice;
        do {
            System.out.println("************************************************");
            System.out.println("**              SOCRATES FR                   **");
            System.out.println("************************************************");
            System.out.println("**                                            **");
            System.out.println("** 1 - Récupérer la liste des emails candidat **");
            System.out.println("** 2 - Ajouter un candidat                    **");
            System.out.println("** 3 - Envoyer les emails de confirmation     **");
            System.out.println("** 0 - Quitter                                **");
            System.out.println("**                                            **");
            System.out.println("************************************************");

            choice = scanner.nextInt();
            scanner.nextLine(); // retrieve \n from the previous nextInt()

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
            }

            System.out.println("Taper Entrer pour continuer ........\n");
            System.in.read();
        } while (choice != 0);

        System.exit(0);
    }

    private static void sendConfirmations() {
        String message;
        if (confirmationSender.send() == EmailStatus.NO_EMAIL_SENT) {
            message = "A jour. Aucun mails de confirmation à envoyer.";
        } else {
            message = "Les emails de confirmation ont été envoyés.";
        }
        System.out.println(message);
    }

    private static void init() {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();

        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        EmailSender emailSender = new SMTPEmailSender(properties);
        DefaultLogger defaultLogger = new DefaultLogger();
        DefaultEmailArchiver defaultArchiveEmail = new DefaultEmailArchiver();
        confirmationSender = new ConfirmationSender(defaultCandidateRepository, emailSender, defaultLogger, defaultArchiveEmail);
    }

    private static void addCandidate() throws IOException {
        System.out.println(CANDIDATE_FIRST_NAME);
        String firstNameValue = scanner.nextLine();
        System.out.println(CANDIDATE_EMAIL_MESSAGE);
        String emailValue = scanner.nextLine();
        Email email;
        try {
            email = Email.of(emailValue);
            Candidate candidate = new Candidate(email, firstNameValue);
            candidateRegistrationManager.register(candidate);
            System.out.println(CANDIDATE_ADDED_MESSAGE);
        } catch (Exception e) {
            System.out.println(NOT_VALID_EMAIL_MESSAGE);
        }
    }

    private static void showCandidatesEmail() {
        Collection<Email> emails = candidateRegistrationManager.findEmails();
        if (emails.isEmpty()) {
            System.out.println(NO_FOUND_CANDIDATE_MESSAGE);
        } else {
            emails.forEach(System.out::println);
        }
    }
}
