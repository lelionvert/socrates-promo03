package com.lacombe.promo3.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid CandidatDto candidatDto) {
        Candidate candidate = candidateService.addCandidate(candidatDto.getEmail().getEmail());
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{email}")
            .buildAndExpand(candidate.getEmail()).toUri();
        return ResponseEntity.created(location).build();
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

    @GetMapping("/emails")
    public ResponseEntity<String> getEmail() {
        String email = candidateService.getEmail();
        return ResponseEntity.ok(email);
    }
}
