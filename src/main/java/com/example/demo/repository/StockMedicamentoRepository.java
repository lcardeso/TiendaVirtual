package com.example.demo.repository;

import com.example.demo.domain.StockMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface StockMedicamentoRepository extends JpaRepository<StockMedicamento, Long> {


}
