package com.example.demo.repository;

import com.example.demo.domain.Alumno;
import com.example.demo.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    @Query("Select u" +
            " FROM Profesor u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombreProfesor, '%') "
    )
    List<Profesor> findByNombre(@Param("nombreProfesor") String nombre);

    Optional<Profesor> findByCatDocente(String catDocente);

    Optional<Profesor> findByDni(String dni);
}
