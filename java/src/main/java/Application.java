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
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

class Application {

    private final PrintStream out;
    private final Scanner in;
    private final String CANDIDATE_FIRST_NAME = "Prénom du candidat :";
    private final String CONFIRMATIONS_EMAIL_ARE_SENT = "Les emails de confirmation ont été envoyés.";

    private final String NO_CONFIRMATIONS_EMAIL_SENT = "A jour. Aucun mails de confirmation à envoyer.";
    private CandidateRegistrationManager candidateRegistrationManager;
    private ConfirmationSender confirmationSender;
    private final InputStream in1;

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
            }

            out.println("Taper Entrer pour continuer ........\n");
            in1.read();
        } while (choice != 0);
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
            isAdded = candidateRegistrationManager.register(candidate);
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

    private void showCandidatesEmail() {
        Collection<Email> emails = candidateRegistrationManager.findEmails();
        if (emails.isEmpty()) {
            String NO_FOUND_CANDIDATE_MESSAGE = "Aucun candidat trouvé.";
            out.println(NO_FOUND_CANDIDATE_MESSAGE);
        } else {
            emails.forEach(out::println);
        }
    }
}
