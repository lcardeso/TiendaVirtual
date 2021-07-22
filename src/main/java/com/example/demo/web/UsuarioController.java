package com.example.demo.web;

import com.example.demo.DTO.ActualizarGrupoDto;
import com.example.demo.DTO.BusquedaLikeUsuario;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.domain.Usuario;
import com.example.demo.sevice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudio")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/obtenerTodos")
    public List<Usuario> obtenerTodos() {
        return usuarioService.getAllUsuario();
    }

    @DeleteMapping("/eliminarUsuario")
    public void deleteUsuario(long delusuario) {
        usuarioService.eliminar(delusuario);
    }


    @PostMapping(value = "/mostarUsuarioPorSexo", params = "sexo")
    public ResponseEntity<List<Usuario>> motrarUsuarioPorSexo(@RequestParam("sexo") String sexo) throws Exception {
        return ResponseEntity.ok().body(usuarioService.usuariosPorSexo(sexo));
    }


    /**
     * Muestra Usuarios Mayores de una edad determinada a traves del metodo POST
     */
    /**
     * Error al enviar los datos por POST ****2021-04-23 04:44:34.572  WARN 6772 --- [nio-8106-exec-5] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.UnsatisfiedServletRequestParameterException: Parameter conditions "edad" not met for actual request parameters: ]
     **/
    @PostMapping(value = "/mayoresDeterminadaEdad", params = "edad")
    public ResponseEntity<List<Usuario>> mayoresDeterminadaEdad(@Valid @RequestBody @RequestParam("edad") Long edad) throws Exception {
        return ResponseEntity.ok().body(usuarioService.mayoresDeterminadaEdad(edad));
    }

    /**
     * Muestra Usuarios de una eterminada edad a traves del metodo POST
     */
    @PostMapping(value = "/edadX", params = "edad")
    public ResponseEntity<List<Usuario>> edadX(@RequestParam("edad") Long edad) throws Exception {
        return ResponseEntity.ok().body(usuarioService.edadX(edad));
    }

    /**
     * Busca un Usuario por nombre
     */

    @PostMapping(value = "/usuario/buscar/Like/nombre")
    public ResponseEntity<List<Usuario>> usuarioXNombre(@Valid @RequestBody BusquedaLikeUsuario busquedaLikeUsuario) {
        return ResponseEntity.ok().body(usuarioService.BuscarUsuario(busquedaLikeUsuario.getNombre()));
    }


    @PostMapping("/usuario/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok().body(usuarioService.adicionar(usuarioDTO));
    }

    @PostMapping("/usuario/actualizar/grupo")
    public ResponseEntity<ResponseDto> actualizar(@Valid @RequestBody ActualizarGrupoDto actualizarGrupoDto) {
        return ResponseEntity.ok().body(usuarioService.actualizarGrupo(actualizarGrupoDto));
    }
}


