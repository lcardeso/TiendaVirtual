package com.example.demo.repository;

import com.example.demo.domain.Pasante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PasantiaRepository extends JpaRepository<Pasante, Long> {



}
