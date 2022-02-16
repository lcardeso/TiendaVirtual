package com.example.demo.repository;

import com.example.demo.domain.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {


    Optional<Grupo> findByNombre(String nombre);

    Optional<Grupo> findByNombreAndCategoria(String nombre, String categoria);
}
