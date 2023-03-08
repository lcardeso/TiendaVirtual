package com.example.demo.repository;

import com.example.demo.DTO.PersonaDTO;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCedula(String cedula);



}
