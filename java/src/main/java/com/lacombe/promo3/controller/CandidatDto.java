package com.lacombe.promo3.controller;

import javax.validation.Valid;

public class CandidatDto {

    @Valid
    private EmailDto email;

    public CandidatDto() {
    }

    public CandidatDto(EmailDto email) {

        this.email = email;
    }

    public EmailDto getEmail() {
        return email;
    }

    public void setEmail(EmailDto email) {
        this.email = email;
    }
}
