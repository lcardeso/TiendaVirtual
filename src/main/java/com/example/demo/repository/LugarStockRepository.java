package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.LugarStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface LugarStockRepository extends JpaRepository<LugarStock, Long> {

    @Query("Select u.nombre" +
            " FROM LugarStock u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombre, '%') "
    )
    List<String> findByNombreLike(@Param("nombre") String nombre);

    Optional<LugarStock> findByNombreAndUbicacion(String nombre, String ubicacion);
}
