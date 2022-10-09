package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Persona;
import com.example.demo.sevice.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudio")

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/persona/obtenerTodos")
    public List<Persona> obtenerTodos() {
        return personaService.obtenerTodos();
    }




/*
   @DeleteMapping("/usuario/eliminar")
    public ResponseEntity<ResponseDto> deleteUsuario(@RequestParam Long idUsuario) {
        return ResponseEntity.ok().body(personaService.eliminar(idUsuario));
    }

    @PostMapping(value = "/mostarUsuarioPorSexo")
    public ResponseEntity<List<Persona>> motrarUsuarioPorSexo(@RequestBody BusquedaPersonaSexoDTO busquedaPersonaSexoDTO) throws Exception {
        return ResponseEntity.ok().body(personaService.usuariosPorSexo(busquedaPersonaSexoDTO.getSexo()));
    }

    @PostMapping(value = "/edadX")
    public ResponseEntity<List<Persona>> edadX(@RequestBody BusquedaPersonaEdadDTO busquedaPersonaEdadDTO) throws Exception {
        return ResponseEntity.ok().body(personaService.edadX(busquedaPersonaEdadDTO.getEdad()));
    }


    @PostMapping(value = "/usuario/buscar/Like/nombre")
    public ResponseEntity<List<Persona>> usuarioXNombre(@Valid @RequestBody BusquedaLikePersona busquedaLikePersona) {
        return ResponseEntity.ok().body(personaService.buscarUsuario(busquedaLikePersona.getNombre()));
    }

    @PostMapping("/usuario/actualizar/grupo")
    public ResponseEntity<ResponseDto> actualizarGrupoUsuario(@Valid @RequestBody ActualizarGrupoDto actualizarGrupoDto) {
        return ResponseEntity.ok().body(personaService.actualizarGrupo(actualizarGrupoDto));
    }

    @PostMapping("/usuario/actualizar/direccion")
    public ResponseEntity<ResponseDto> actualizarDireccionUsuario(@Valid @RequestBody ActualizarDireccionDto actualizarDireccionDto) {
        return ResponseEntity.ok().body(personaService.actualizarDireccion(actualizarDireccionDto));
    }
*/





}