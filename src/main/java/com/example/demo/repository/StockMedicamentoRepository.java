package com.example.demo.repository;

import com.example.demo.domain.StockMedicamento;
import com.example.demo.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StockMedicamentoRepository extends JpaRepository<StockMedicamento, Long> {




}
