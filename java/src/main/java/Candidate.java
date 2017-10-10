import java.util.Objects;

public class Candidate {

    private Email email;

    static Candidate of(Email mail) {
        return new Candidate(mail);
    }

    private Candidate(Email email){
        this.email = email;
    }

    Email getMail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(email, candidate.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
