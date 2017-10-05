
public class Email {

    private String email;

    public Email(String email) {
        verifyEmail(email);
        this.email = email;
    }

    private void verifyEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        return email.equals(email1.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
