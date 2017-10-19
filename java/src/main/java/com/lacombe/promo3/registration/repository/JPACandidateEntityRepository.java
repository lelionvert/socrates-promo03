package com.lacombe.promo3.registration.repository;

import com.lacombe.promo3.registration.model.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACandidateEntityRepository extends JpaRepository<CandidateEntity, Integer> {
}
