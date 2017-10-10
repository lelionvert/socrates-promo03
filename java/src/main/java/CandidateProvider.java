import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class CandidateProvider {

    private final Collection<Candidate> candidates = new HashSet<>();

    boolean isEmpty() {
        return candidates.isEmpty();
    }

    void add(Candidate... candidates) {
        Collections.addAll(this.candidates, candidates);
    }

    boolean exist(Candidate... candidates) {
        for (Candidate candidateCandidate : candidates) {
            if(!this.candidates.contains(candidateCandidate))
                return false;
        }
        return true;
    }

    int size() {
        return candidates.size();
    }

    Collection<Candidate> getCandidates() {
        return Collections.unmodifiableCollection(candidates);
    }
}
