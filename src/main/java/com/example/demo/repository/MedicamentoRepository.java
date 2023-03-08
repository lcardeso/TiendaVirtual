package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Optional<Medicamento> findByNombre(String nombre);

    @Modifying
    @Query(value = "UPDATE farmacia.stock_medicamento " +
            "SET cantidad = cantidad - :cant " +
            "WHERE id_medicamento_FK = (SELECT m.id_medicamento " +
            "from medicamento m " +
            "where m.nombre = :nombre);", nativeQuery = true)
    void updateCantidad(@Param("nombre") String nombre, @Param("cant") Integer cant);
}
