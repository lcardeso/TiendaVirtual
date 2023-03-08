package com.example.demo.repository;

import com.example.demo.domain.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DireccionRepository extends JpaRepository<Direccion, Long> {


    Optional<Direccion> findByRefCastral(Integer refCastral);
}