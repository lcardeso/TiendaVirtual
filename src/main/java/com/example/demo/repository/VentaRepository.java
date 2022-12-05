package com.example.demo.repository;

import com.example.demo.domain.Automovil;
import com.example.demo.domain.Persona;
import com.example.demo.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository

public interface VentaRepository extends JpaRepository<Venta, Long> {

/*    Optional<LocalDateTime> findByFechaVenta(LocalDateTime fechaVenta);

    List<Venta> findByEstadoVenta(String codigo);

    @Query(value = " Select v FROM Venta v where MONTH (fecha_venta) = :mes"
    )
    List<Venta> findByMes(@Param("mes") Integer mes);

    @Query(value = " Select v.automovil FROM Venta v where MONTH(v.fechaVenta) = MONTH(:fecha) AND YEAR(v.fechaVenta) = YEAR(:fecha) AND v.estadoVenta = 'R' "
    )
    List<Automovil> findByAutosPorMes(@Param("fecha") LocalDate fecha);

    @Query(value = " Select COUNT(v.id) FROM Venta v JOIN v.persona p WHERE v.estadoVenta = 'C' AND p.cedula = :cedula "
    )
    long findByCantCancelaciones(@Param("cedula") String cedula);

    @Query(value = " Select p FROM Venta v JOIN v.persona p WHERE v.estadoVenta = 'C' " +
            "GROUP BY p.id " +
            "HAVING COUNT(v.id) > 2"
    )
    List<Persona> findByCantPersonasMorosas();*/

}
