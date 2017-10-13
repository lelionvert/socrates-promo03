package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CandidatesController {

    private CandidateService candidateService;

    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    //@ApiOperation(value = "Get All Candidates", response = Collection.class)
    @GetMapping("/candidates")
    public ResponseEntity<Collection<Candidate>> getCandidates() {
        Collection<Candidate> candidates = candidateService.getCandidates();
        return ResponseEntity.ok(candidates);
    }

    //@ApiOperation(value = "Get a candidate", response = Candidate.class)
    @GetMapping("/candidates/{email:.+}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable String email) {
        Optional<Candidate> candidate = candidateService.getItem(email);
        if(candidate.isPresent()) {
            return ResponseEntity.ok().body(candidate.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/candidates")
    public ResponseEntity<?> addCandidate(@RequestBody Candidate candidate) {
        String email = candidateService.addCandidate(candidate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(candidate.getEmail().getEmailString()).toUri();
        return ResponseEntity.created(location).build();
    }

}
