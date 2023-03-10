package com.example.demo.repository;

import com.example.demo.domain.MotivoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MotivoStockRepository extends JpaRepository<MotivoStock, Long> {


    Optional<MotivoStock> findByCodigo(String codigo);
}
