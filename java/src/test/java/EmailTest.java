import org.junit.Test;

public class EmailTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_there_is_an_empty_email() throws Exception {
        Email.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_there_is_no_email() throws Exception {
        Email.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_email_has_no_at() throws Exception {
        Email.of("emailNotValid.ok");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_email_has_no_domain_name() throws Exception {
        Email.of("emailNotValid");
    }

}
