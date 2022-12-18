package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {


}
