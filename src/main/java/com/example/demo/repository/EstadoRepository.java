package com.example.demo.repository;

import com.example.demo.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByCodigo(String codigo);





}
