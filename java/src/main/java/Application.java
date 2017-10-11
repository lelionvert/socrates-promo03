import com.lacombe.promo3.registration.CandidateRegistrationManager;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Application {

    private static CandidateRegistrationManager candidateRegistrationManager;

    public static void main(String[] args) throws IOException {
        initManager();

        Scanner scanner = new Scanner(System.in);
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

            switch (choice) {
                case 1 : showCandidatesEmail(); break;
                case 2 : addCandidate(); break;
            }

            System.in.read();
        } while (choice != 0);

        System.exit(0);
    }

    private static void initManager() {
        DefaultCandidateRepository defaultCandidateRepository = new DefaultCandidateRepository();
        candidateRegistrationManager = new CandidateRegistrationManager(defaultCandidateRepository);
    }

    private static void addCandidate() {
        throw new NotImplementedException();
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
