package com.example.demo.repository;

import com.example.demo.domain.Direccion;
import com.example.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    List<Usuario> findByActivo(Boolean estado);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByUsuarioAndContrasenna(String usuario, String contrasenna);
}
