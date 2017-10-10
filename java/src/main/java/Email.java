import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by valmey on 10/10/2017.
 */
public class Email {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Predicate<String> isEmptyOrNull = s -> s == null || s.isEmpty(); //isBlank String.Utils Apache
    private String email;

    public Email(String email) {
        this.email = email;
    }

    static Email of(String email) {
        verifyEmail(email);
        return new Email(email);
    }

    public static void verifyEmail(String email) {
        if(isEmptyOrNull.test(email)
            || !isValidFormat(email)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isValidFormat(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
