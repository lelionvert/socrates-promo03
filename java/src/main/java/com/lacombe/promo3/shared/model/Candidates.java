package com.lacombe.promo3.shared.model;

import com.lacombe.promo3.shared.model.Candidate;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class Candidates {

    public void forEach(Consumer<? super Candidate> action) {
        candidates.forEach(action);
    }

    private Collection<Candidate> candidates;

    public Candidates(Collection<Candidate> candidates) {
        this.candidates = candidates;
    }

    public boolean isEmpty() {
        return candidates.isEmpty();
    }

    public Collection<Candidate> getCandidates() {
        return Collections.unmodifiableCollection(candidates);
    }
}
