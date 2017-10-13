package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.controller.dto.EmailDTO;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.service.CandidateService;
import com.lacombe.promo3.registration.service.LunchService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CandidatesController {

    private static final ModelMapper modelMapper = new ModelMapper();

    private CandidateService candidateService;
    private LunchService lunchService;

    public CandidatesController(CandidateService candidateService, LunchService lunchService) {
        this.candidateService = candidateService;
        this.lunchService = lunchService;
    }


    @GetMapping("/emails")
    @ApiOperation(value = "Candidates emails list", response = Iterable.class)
    public ResponseEntity<Collection<Email>> getCandidatesEmails(){
        Collection<Email> candidates =
                candidateService.getEmails();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/candidate")
    @ApiOperation(value = "Get candidate with email", response = Candidate.class)
    public ResponseEntity<Candidate> get(@Valid @RequestParam String email){
       Candidate candidate =
                candidateService.getCandidateByEmail(email);
        return ResponseEntity.ok(candidate);
    }

    @PostMapping("/candidate")
    @ApiOperation(value = "Add a candidate with email", response = Candidate.class)
    public ResponseEntity<Candidate> addCandidate(@Valid @RequestBody  EmailDTO emailDTO) {
        System.out.println("dto " + emailDTO.getEmail());
        Email email = modelMapper.map(emailDTO, Email.class);
        System.out.println("entity " + email.getEmail());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(candidateService.add(email)
                        .getEmail()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/lunch")
    @ApiOperation(value = "Candidates lunch")
    public ResponseEntity<?> getCandidatesLunch(@Valid @RequestParam String email){
        return ResponseEntity.ok(lunchService.getLunch(email));
    }
}
