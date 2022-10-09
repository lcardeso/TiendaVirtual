package com.example.demo.repository;

import com.example.demo.domain.Alumno;
import com.example.demo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("Select u" +
            " FROM Persona u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombreUsuario, '%') "
    )
    List<Persona> findByNombre(@Param("nombreUsuario") String nombre);

    List<Persona> findBySexo(String sexo);

    List<Persona> findByEdad(Integer edad);

    Optional<Persona> findByNombreAndPrimApellido(String nombre, String primApellido);





}
