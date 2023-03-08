package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {


    Optional<Laboratorio> findByNombre(String nombre);
}
