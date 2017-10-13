package com.lacombe.promo3.registration.controller;

import com.lacombe.promo3.registration.model.Email;

import javax.validation.constraints.NotNull;

public class CandidateForm {

    @NotNull
    private Email email;

    public Email getEmail() {
        return email;
    }
}
