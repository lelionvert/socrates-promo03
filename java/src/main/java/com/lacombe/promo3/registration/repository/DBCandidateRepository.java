package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;

public class DBCandidateRepository implements CandidateRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Candidate candidate) {
        final int update = jdbcTemplate.update("INSERT INTO Candidate(email) VALUES (?)", candidate.getEmail().toString());
        if(update != 1) {
            throw new IllegalStateException("Not enough line modified " + update);
        }
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        Collection<String> emailsReturn = jdbcTemplate.queryForList("SELECT email FROM candidate", String.class);
        Collection<Email> emails = new ArrayList<>();
        for(String email : emailsReturn) {
            emails.add(Email.valueOf(email));
        }
        if(emails.isEmpty()) {
            throw new IllegalStateException("No data found");
        }
        return emails;
    }

    @Override
    public Collection<Candidate> getCandidates() {
        return null;
    }
}
