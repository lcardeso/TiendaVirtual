package com.example.demo.repository;

import com.example.demo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("Select u" +
            " FROM Persona u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombre, '%') "
    )
    List<Persona> findByNombre(@Param("nombre") String nombre);

    Optional<Persona> findByCedula(String cedula);

    List<Persona> findBySexo(String sexo);

    @Query("Select u.cedula" +
            " FROM Persona u where UPPER(REPLACE(u.cedula,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:cedula, '%') "
    )
    List<String> findByCedulaLike(@Param("cedula") String cedula);

  //  Optional<Persona> findByNombreAndPrimApellido(String nombre, String primApellido);





}
