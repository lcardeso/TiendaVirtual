package com.example.demo.DTO;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.domain.Usuario;

public interface UsuarioDAO extends CrudRepository< Usuario, Long>{

}
