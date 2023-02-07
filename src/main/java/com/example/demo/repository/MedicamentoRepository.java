package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

     Optional<Medicamento> findByNombre(String nombre);

}
