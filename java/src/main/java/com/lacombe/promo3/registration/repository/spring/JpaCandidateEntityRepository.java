package com.lacombe.promo3.registration.repository.spring;

import com.lacombe.promo3.registration.repository.spring.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCandidateEntityRepository extends JpaRepository<CandidateEntity, Long> {


}