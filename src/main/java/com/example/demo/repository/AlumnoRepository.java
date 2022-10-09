package com.example.demo.repository;

import com.example.demo.domain.Alumno;
import com.example.demo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("Select u" +
            " FROM Alumno u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombreAlumno, '%') "
    )
    List<Alumno> findByNombre(@Param("nombreAlumno") String nombre);

    Optional<Alumno> findByNumExpediente(String numExpediente);


    Optional<Alumno> findByDni(String dni);
}
