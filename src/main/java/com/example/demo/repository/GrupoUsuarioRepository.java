package com.example.demo.repository;

import com.example.demo.domain.GrupoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Long> {


}
