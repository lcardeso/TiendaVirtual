package com.example.demo.web;

import com.example.demo.DTO.*;
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

    @GetMapping("/usuario/obtenerTodos")
    public List<Usuario> obtenerTodos() {
        return usuarioService.getAllUsuario();
    }

    @DeleteMapping("/usuario/eliminar")
    public ResponseEntity<ResponseDto> deleteUsuario(@RequestParam Long idUsuario) {
        return ResponseEntity.ok().body(usuarioService.eliminar(idUsuario));
    }


    @PostMapping(value = "/mostarUsuarioPorSexo")
    public ResponseEntity<List<Usuario>> motrarUsuarioPorSexo(@RequestBody BusquedaUsuarioSexoDTO busquedaUsuarioSexoDTO) throws Exception {
        return ResponseEntity.ok().body(usuarioService.usuariosPorSexo(busquedaUsuarioSexoDTO.getSexo()));
    }


    @PostMapping(value = "/edadX")
    public ResponseEntity<List<Usuario>> edadX(@RequestBody BusquedaUsuarioEdadDTO busquedaUsuarioEdadDTO) throws Exception {
        return ResponseEntity.ok().body(usuarioService.edadX(busquedaUsuarioEdadDTO.getEdad()));
    }

    // Busca un Usuario por nombre
    @PostMapping(value = "/usuario/buscar/Like/nombre")
    public ResponseEntity<List<Usuario>> usuarioXNombre(@Valid @RequestBody BusquedaLikeUsuario busquedaLikeUsuario) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuario(busquedaLikeUsuario.getNombre()));
    }

    @PostMapping("/usuario/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok().body(usuarioService.validarUsuario(usuarioDTO));
    }

    @PostMapping("/usuario/actualizar/grupo")
    public ResponseEntity<ResponseDto> actualizarGrupoUsuario(@Valid @RequestBody ActualizarGrupoDto actualizarGrupoDto) {
        return ResponseEntity.ok().body(usuarioService.actualizarGrupo(actualizarGrupoDto));
    }

    @PostMapping("/usuario/actualizar/direccion")
    public ResponseEntity<ResponseDto> actualizarDireccionUsuario(@Valid @RequestBody ActualizarDireccionDto actualizarDireccionDto) {
        return ResponseEntity.ok().body(usuarioService.actualizarDireccion(actualizarDireccionDto));
    }
}


/**
 * Muestra Usuarios Mayores de una edad determinada a traves del metodo POST
 *
 * @PostMapping(value = "/mayoresDeterminadaEdad")
 * public ResponseEntity<List<Usuario>> mayoresDeterminadaEdad(@Valid @RequestBody BusquedaUsuarioEdadDTO busquedaUsuarioEdadDTO) throws Exception {
 * return ResponseEntity.ok().body(usuarioService.mayoresDeterminadaEdad(busquedaUsuarioEdadDTO.getEdad()));
 * }
 */

