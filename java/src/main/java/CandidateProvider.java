import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class CandidateProvider {

    private final Collection<Candidate> candidates = new HashSet<>();

    public boolean isEmpty() {
        return candidates.isEmpty();
    }

    public void add(Candidate... candidates) {
        Collections.addAll(this.candidates, candidates);
    }

    public boolean exist(Candidate... candidates) {
        for (Candidate candidateCandidate : candidates) {
            if(!this.candidates.contains(candidateCandidate))
                return false;
        }
        return true;
    }

    public int size() {
        return candidates.size();
    }

    public Collection<Candidate> getCandidates() {
        return Collections.unmodifiableCollection(candidates);
    }
}
