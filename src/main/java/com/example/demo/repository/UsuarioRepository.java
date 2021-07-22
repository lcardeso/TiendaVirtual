package com.example.demo.repository;

import com.example.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("Select u" +
            " FROM Usuario u where UPPER(REPLACE(u.nombre,'ÁáÉéÍíÓóÚú','AaEeIiOoUu' )) like CONCAT( '%' ,:nombreUsuario, '%') "
    )
    List<Usuario> findByNombre(@Param("nombreUsuario") String nombre);

    List<Usuario> findBySexo(String sexo);

    List<Usuario> findByEdad(long edad);


}
