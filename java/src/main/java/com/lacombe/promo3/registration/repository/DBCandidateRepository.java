package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCandidateRepository implements CandidateRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Candidate candidate) {
        jdbcTemplate.update("INSERT INTO candidate(email) VALUES (?)", candidate.getEmail().getEmailString());
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        String selectQuery = "SELECT email FROM candidate";

        List<Email> result = jdbcTemplate.query(selectQuery, (row, rownum) -> Email.of(row.getString(1)));

        return result;
    }

    @Override
    public Collection<Candidate> getCandidates() {

        return Collections.emptyList();
    }

    @Override
    public Optional<Candidate> getCandidate(String email) {
        return Optional.empty();
    }
}
