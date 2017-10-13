package com.lacombe.promo3.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.toIntExact;

@RestController
@RequestMapping("/api")
public class CandidatesController {

    private CandidateService candidateService;

    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public ResponseEntity<Collection<Candidate>> getCandidates() {
        Collection<Candidate> candidates = candidateService.getCandidates();
        return ResponseEntity.ok(candidates);
    }

    @PostMapping("/candidate")
    public void addCandidate(String email) {
        candidateService.addCandidate(email);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> getOneCandidateById(@PathVariable long id) {
        List<Candidate> candidates =  new ArrayList<>(candidateService.getCandidates());
        final int index = toIntExact(id);
        if ( index <= candidates.size()){
            return ResponseEntity.ok(candidates.get(index));
        }
        return ResponseEntity.notFound().build();
    }
}
