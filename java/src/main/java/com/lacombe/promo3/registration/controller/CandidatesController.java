package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<?> addCandidate(@RequestBody @Valid CandidateForm candidateForm) {
        String email = candidateService.addCandidate(candidateForm.getEmail());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(email).toUri();
        return ResponseEntity.created(location).build();
    }

}
