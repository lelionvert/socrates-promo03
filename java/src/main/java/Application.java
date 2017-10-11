import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Application {

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
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);
    }

    private static void addCandidate() throws IOException {
        System.out.println("Email du candidat :");
        String emailValue = scanner.nextLine();
        Email email;
        try {
            email = Email.of(emailValue);
            Candidate candidate = new Candidate(
                    email
            );
            candidateRegistrationManager.register(candidate);
            System.out.println("Le candidat a été ajouté.");
        } catch (Exception e) {
            System.out.println("L'email n'est pas valide.");
        }
    }

    private static void showCandidatesEmail() {
        Collection<Email> emails = candidateRegistrationManager.findEmails();
        if(emails.isEmpty()) {
            System.out.println("Aucun candidat trouvé.");
        } else {
            emails.forEach(System.out::println);
        }
    }
}
