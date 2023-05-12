package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario/cliente")

public class UsuarioController {

   /* @Autowired
    public UsuarioService usuarioService;


    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok().body(usuarioService.listar());
    }

    @GetMapping("/listarUsuariosPorEstado")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosPorEstado(Boolean estado) {
        return ResponseEntity.ok().body(usuarioService.listarUsuariosPorEstado(estado));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok().body(usuarioService.adicionar(usuarioDTO));
    }


    @GetMapping("/reiniciarContrasenna")
    public ResponseEntity<ResponseDto> reiniciarContrasenna(@RequestParam Long idUsuario) {
        return ResponseEntity.ok().body(usuarioService.reiniciarContrasenna(idUsuario));
    }

    @GetMapping("/modRol")
    public ResponseEntity<ResponseDto> modRol(@RequestParam Long idUsuario, @RequestParam Long idRol) {
        return ResponseEntity.ok().body(usuarioService.modRol(idUsuario, idRol));
    }

    @GetMapping("/modEstadoUsuario")
    public ResponseEntity<ResponseDto> modEstadoUsuario(@RequestParam Long idUsuario, @RequestParam Boolean estado) {
        return ResponseEntity.ok().body(usuarioService.modEstadoUsuario(idUsuario, estado));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUsuarioDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok().body(usuarioService.login(loginDTO));
    }*/
}