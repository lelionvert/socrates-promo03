import org.junit.Test;

public class EmailTest {

    @Test(expected = IllegalArgumentException.class)
    public void emptyEmailThrowException() throws Exception {
        Email.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailThrowException() throws Exception {
        Email.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmailValidThrowException() throws Exception {
        Email.of("emailNotValid.ok");
    }
}
