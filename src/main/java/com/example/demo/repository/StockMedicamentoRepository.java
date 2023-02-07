package com.example.demo.repository;

import com.example.demo.domain.Medicamento;
import com.example.demo.domain.StockMedicamento;
import com.example.demo.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StockMedicamentoRepository extends JpaRepository<StockMedicamento, Long> {


    Integer findByCantidad(String nombre);

    /*@Query("UPDATE StockMedicamento SET cantidad = cantidad - :cant where id_medicamento_FK = " +
            "(SELECT m.id from Medicamento m where m.nombre = :nombre)")
    Boolean updateCantidad(@Param("nombre") String nombre, @Param("cant") Integer cant);*/

    @Modifying
    @Query(value = "UPDATE farmacia.stock_medicamento " +
            "SET cantidad= cantidad - :cant " +
            "WHERE id_medicamento_FK = (SELECT m.id_medicamento " +
            "from medicamento m " +
            "where m.nombre = :nombre);", nativeQuery = true)
    void updateCantidad(@Param("nombre") String nombre, @Param("cant") Integer cant);
    /*@Query("UPDATE sm SET sm.cantidad = sm.cantidad - :cant" +
            " from StockMedicamento sm join sm.medicamento m" +
            " where m.nombre = :nombre")
    Boolean updateCantidad(@Param("nombre") String nombre, @Param("cant") Integer cant);*/
}
