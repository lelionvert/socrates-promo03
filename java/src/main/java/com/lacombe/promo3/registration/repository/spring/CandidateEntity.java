package com.lacombe.promo3.registration.repository.spring;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String emailString;

    public CandidateEntity(String emailString) {
        this.emailString = emailString;
    }

    //JPA is using it
    protected CandidateEntity() {

    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, email='%s']",
                id, emailString);
    }

    public void setEmail(String email) {
        this.emailString = email;
    }
}
