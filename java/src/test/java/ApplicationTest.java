import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationTest {

    private final String NO_ADD_CANDIDATE = "************************************************\n" +
            "**              SOCRATES FR                   **\n" +
            "************************************************\n" +
            "**                                            **\n" +
            "** 1 - Récupérer la liste des emails candidat **\n" +
            "** 2 - Ajouter un candidat                    **\n" +
            "** 3 - Envoyer les emails de confirmation     **\n" +
            "** 0 - Quitter                                **\n" +
            "**                                            **\n" +
            "************************************************\n" +
            "Aucun candidat trouvé.\n" +
            "Taper Entrer pour continuer ........\n" +
            "\n" +
            "************************************************\n" +
            "**              SOCRATES FR                   **\n" +
            "************************************************\n" +
            "**                                            **\n" +
            "** 1 - Récupérer la liste des emails candidat **\n" +
            "** 2 - Ajouter un candidat                    **\n" +
            "** 3 - Envoyer les emails de confirmation     **\n" +
            "** 0 - Quitter                                **\n" +
            "**                                            **\n" +
            "************************************************\n" +
            "Taper Entrer pour continuer ........\n" +
            "\n";

    @Ignore
    @Test
    public void should_add_a_candidate() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        byte[] buf = "1\n\n0\n".getBytes();
        Application application = new Application(out, new ByteArrayInputStream(buf));
        application.mainBlock();
        String screen = baos.toString();
        Assertions.assertThat(screen).isEqualTo(NO_ADD_CANDIDATE);
    }
}