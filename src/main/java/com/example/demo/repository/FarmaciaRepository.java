package com.example.demo.repository;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.LugarStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {


}
