public class Candidate {

    private final Email email;

    static Candidate ofCandidate(Email of) {
        return new Candidate(of);
    }

    private Candidate(Email of) {
        this.email = of;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return email != null ? email.equals(candidate.email) : candidate.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public Email getEmail() {
        return email;
    }
}
