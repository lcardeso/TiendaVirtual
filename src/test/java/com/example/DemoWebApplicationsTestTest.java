package com.example;

import com.example.demo.UsuarioApplication;
import com.example.demo.domain.Persona;
import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsuarioApplication.class)
class DemoWebApplicationsTestTest {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


/*    @Test
    public void crearUsuarioTest() {
        Usuario us = new Usuario();
        Optional<Rol> rol = rolRepository.findByDescripcion("Admin");
        Optional<Persona> persona = personaRepository.findById(1L);
        us.setUsuario("lis");
        us.setContrasenna(encoder.encode("123"));
        us.setActivo(Boolean.TRUE);
        us.setRol(rol.get());
        us.setPersona(persona.get());
        Usuario retorno = usuarioRepository.save(us);
        assertTrue(retorno.getContrasenna().equalsIgnoreCase(us.getContrasenna()));
    }*/



}