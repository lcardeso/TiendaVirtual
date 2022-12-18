package com.example.demo.repository;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TituloRepository extends JpaRepository<Titulo, Long> {

    Optional<Titulo> findByNumRegistro(Integer numRegistro);


}
