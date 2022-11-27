package com.example.demo.repository;

import com.example.demo.domain.PagoFinanciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PagoFinanciadoRepository extends JpaRepository<PagoFinanciado, Long> {
    Optional<PagoFinanciado> findByTipoFinanciacion(String tipo);



  /*  @Query("Select u" +
            " FROM Automovil u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:modelo, '%') "
    )
    List<Automovil> findByModelo(@Param("modelo") String modelo);

    Optional<Automovil> findByMatricula(String matricula);

*/


}
