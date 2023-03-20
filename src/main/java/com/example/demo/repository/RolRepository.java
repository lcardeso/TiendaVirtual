package com.example.demo.repository;

import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface RolRepository extends JpaRepository<Rol, Long> {


    Optional<Rol> findByDescripcion(String rolDescripcion);
    //Optional<List<Rol>> findByDescripcion(String rolDescripcion);

}
