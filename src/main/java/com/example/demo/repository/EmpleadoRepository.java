package com.example.demo.repository;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByCedula(String cedula);

    @Query("Select u.cedula" +
            " FROM Empleado u where UPPER(REPLACE(u.cedula,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:cedula, '%') AND u.permanencia = 'A' "
    )
    List<String> findByCedulaLike(@Param("cedula") String cedula);



    List<Empleado> findByCargo(String cargo);


    Optional<Empleado> findByNombre(String nombre);

    @Query("Select u.nombre" +
            " FROM Empleado u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombre, '%') AND u.permanencia = 'A' "
    )
    List<String> findByNombreLike(@Param("nombre") String nombre);

    List<Empleado> findByPermanencia(String estado);
}
