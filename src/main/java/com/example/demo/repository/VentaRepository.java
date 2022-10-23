package com.example.demo.repository;

import com.example.demo.domain.Automovil;
import com.example.demo.domain.MetodoDePago;
import com.example.demo.domain.Persona;
import com.example.demo.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface VentaRepository extends JpaRepository<Venta, Long> {



  /*  @Query("Select u" +
            " FROM Automovil u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:modelo, '%') "
    )
    List<Automovil> findByModelo(@Param("modelo") String modelo);

    Optional<Automovil> findByMatricula(String matricula);

*/


}