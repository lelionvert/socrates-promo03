import org.junit.Assert;
import org.junit.Test;

public class CandidateRegistrationManagerTest {

    @Test
    public void shouldReturnOneCandidateEmail(){
        String email = "test@test.fr";
        Assert.assertEquals(email, new CandidateRegistrationManager().add(email));
    }
}
