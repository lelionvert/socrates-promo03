package registration.candidate;

public class Email {
    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {
        if(email == null)
            throw new IllegalArgumentException();
        return new Email(email);
    }

    public static Email withoutValidation(String email) {
        return new Email(email);
    }

    public String getEmail() {
        return email;
    }
}
