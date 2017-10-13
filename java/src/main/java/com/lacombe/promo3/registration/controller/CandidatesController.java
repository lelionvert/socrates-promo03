package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CandidatesController {

    CandidateService candidateService;

    public CandidatesController() {
        this.candidateService = new CandidateService();
    }

    @GetMapping("/emails")
    @ApiOperation(value = "Candidates emails list", response = Iterable.class)
    public ResponseEntity<Collection<Email>> getCandidatesEmails(){
        Collection<Email> candidates =
                candidateService.getEmails();
        return ResponseEntity.ok(candidates);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "Add a candidate with email", response = Candidate.class)
    public ResponseEntity<Candidate> addCandidate(@RequestBody String email){
        return ResponseEntity.ok(candidateService.add(email));
    }
}
