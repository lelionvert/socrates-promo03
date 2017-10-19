package com.lacombe.promo3.registration.repository.entity;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
public class CandidateEntity {

    public CandidateEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    public CandidateEntity(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Email toEmail() {
        return Email.of(email);
    }

    public Candidate toCandidate() {
        return new Candidate(Email.of(email));
    }
}
