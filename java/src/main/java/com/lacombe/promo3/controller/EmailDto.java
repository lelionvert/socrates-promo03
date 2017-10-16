package com.lacombe.promo3.controller;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class EmailDto {

    @NotNull
    @Email
    private String email;

    public EmailDto(String email) {
        this.email = email;
    }

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
