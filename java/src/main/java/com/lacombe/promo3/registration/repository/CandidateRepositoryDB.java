package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

// @Repository
public class CandidateRepositoryDB implements CandidateRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Collection<Candidate> findAll(){
        Collection<Candidate> candidates = new ArrayList<>();

        final String query = "SELECT * FROM candidate limit 10";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        results.forEach( row -> {
            Candidate candidate = new Candidate(
                    Email.of((String) row.get("email")));
                    candidates.add(candidate);
                    System.out.println(candidate);
                }
        );

        return candidates;
    }

    @Override
    public void add(Candidate candidate) {
        jdbcTemplate.update("INSERT INTO candidate(email) VALUES (?)"
                , new Object[] {candidate.getEmail()});
    }

    @Override
    public boolean hasAlready(Candidate candidate) {
        return false;
    }

    @Override
    public Collection<Email> getEmails() {
        Collection<Candidate> candidates = findAll();
        return candidates.stream().map(Candidate::getEmail).collect(toSet());
    }

    @Override
    public Candidate getByEmail(String email) {
        String query = "SELECT * FROM candidate WHERE email = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{email},
                new RowMapper<Candidate>() {
                    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Candidate(Email.of(rs.getString("email")));
                    }
                });
    }
}
