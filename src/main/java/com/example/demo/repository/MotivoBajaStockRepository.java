package com.example.demo.repository;

import com.example.demo.domain.MotivoBajaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MotivoBajaStockRepository extends JpaRepository<MotivoBajaStock, Long> {


    Optional<MotivoBajaStock> findByCodigo(String codigo);
}
