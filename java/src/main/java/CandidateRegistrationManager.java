import java.util.*;

class CandidateRegistrationManager {

    private final CandidateRepository candidateRepository;

    public CandidateRegistrationManager(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Collection<Email> findEmails() {
        return candidateRepository.getEmails();
    }

    public void register(Candidate... candidates) {
        for (Candidate candidate : candidates){
            registerOne(candidate);
        }
    }

    private void registerOne(Candidate candidate) {
        if (!candidateRepository.hasAlready(candidate)) {
            candidateRepository.add(candidate);
        }
    }
}
