import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private static Predicate<String> isEmptyOrNull = s -> s == null || s.isEmpty();

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String email;

    public static Email of(String email) {
        verifyEmail(email);
        return new Email(email);
    }

    private Email(String email){
        this.email = email;
    }

    private static void verifyEmail(String email) {
        if(isEmptyOrNull.test(email)
            || !isValidFormat(email)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isValidFormat(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
}
