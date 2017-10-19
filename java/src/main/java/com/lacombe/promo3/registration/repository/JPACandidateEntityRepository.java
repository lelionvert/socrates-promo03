package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.repository.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface JPACandidateEntityRepository extends JpaRepository<CandidateEntity, Long> {

    CandidateEntity findByEmail(String email);
}
