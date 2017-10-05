import org.junit.Test;

public class EmailTest {

    @Test(expected = IllegalArgumentException.class)
    public void emptyEmailThrowException() throws Exception {
        new Email("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailThrowException() throws Exception {
        new Email(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmailValidThrowException() throws Exception {
        new Email("emailNotValid.ok");
    }
}
