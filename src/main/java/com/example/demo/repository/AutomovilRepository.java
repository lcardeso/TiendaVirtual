package com.example.demo.repository;

import com.example.demo.domain.Automovil;
import com.example.demo.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {

/*    Optional<Automovil> findByMatricula(String matricula);

    List<Automovil> findByEstado(Estado id);

    @Query("Select u.matricula" +
            " FROM Automovil u where UPPER(REPLACE(u.matricula,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:matricula, '%') "
    )
    List<String> findByMatriculaLike(@Param("matricula") String matricula);
    */

}
