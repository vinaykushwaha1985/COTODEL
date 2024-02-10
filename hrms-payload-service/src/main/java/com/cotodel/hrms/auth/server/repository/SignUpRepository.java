package com.cotodel.hrms.auth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cotodel.hrms.auth.server.model.SignUpEntity;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpEntity, Long>{
}
