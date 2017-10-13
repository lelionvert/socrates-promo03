package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


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

    @GetMapping("/candidates/{email:.+}")
    public ResponseEntity<Optional<Candidate>> getCandidate(@PathVariable String email) {
        Optional<Candidate> candidate = candidateService.getItem(email);
        return ResponseEntity.ok(candidate);
    }

}
