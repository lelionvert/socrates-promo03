package com.lacombe.promo3.registration.controller;

import javax.validation.constraints.NotNull;

public class CandidateForm {

    public CandidateForm() {
    }

    @NotNull
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
