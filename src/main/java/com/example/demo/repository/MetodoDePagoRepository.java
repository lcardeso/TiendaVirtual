package com.example.demo.repository;

import com.example.demo.domain.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Long> {



  /*  @Query("Select u" +
            " FROM Automovil u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:modelo, '%') "
    )
    List<Automovil> findByModelo(@Param("modelo") String modelo);

    Optional<Automovil> findByMatricula(String matricula);

*/


}
