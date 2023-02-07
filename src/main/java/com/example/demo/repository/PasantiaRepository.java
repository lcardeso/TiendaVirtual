package com.example.demo.repository;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.Pasante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PasantiaRepository extends JpaRepository<Pasante, Long> {


    List<Pasante> findByPermanencia(String estado);

    Optional<Pasante> findByCedula(String cedula);

    @Query("Select u.cedula" +
            " FROM Pasante u where UPPER(REPLACE(u.cedula,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:cedula, '%') "
    )
    List<String> findByCedulaLike(@Param("cedula") String cedula);

    Optional<Pasante> findByNombre(String nombre);

    @Query("Select u.nombre" +
            " FROM Pasante u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombre, '%') "
    )
    List<String> findByNombreLike(@Param("nombre") String nombre);
}
