import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
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
                case 1 : getCandidatesEmail(); break;
                case 2 : addCandidate(); break;
            }

        } while (choice != 0);

        System.exit(0);
    }

    private static void addCandidate() {
        throw new NotImplementedException();
    }

    private static void getCandidatesEmail() {
        throw new NotImplementedException();
    }
}
