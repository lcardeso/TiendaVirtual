package com.example.demo.repository;

import com.example.demo.domain.SolicitudCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SolicitudCompraRepository extends JpaRepository<SolicitudCompra, Long> {


    List<SolicitudCompra> findByEstado(String estado);
}
