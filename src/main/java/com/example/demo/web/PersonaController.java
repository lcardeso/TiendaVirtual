package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/concesionario/persona")

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/listar")
    public ResponseEntity<List<PersonaDTO>> obtener(){
        return ResponseEntity.ok().body(personaService.obtener());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok().body(personaService.adicionar(personaDTO));
    }

    @PostMapping("/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok().body(personaService.modificar(personaDTO));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<ResponseDto> delete(@RequestParam Long idPersona) {
        return ResponseEntity.ok().body(personaService.eliminar(idPersona));
    }

    @GetMapping("/buscarPorCedula")
    public ResponseEntity<PersonaDTO> buscarPorCedula(@RequestParam String cedula) {
        return ResponseEntity.ok().body(personaService.buscarPorCedula(cedula));
    }

    @GetMapping("/buscarPorCedulaLike")
    public ResponseEntity<List<String>> buscarCedulaLike( @RequestParam String cedula) {
        return ResponseEntity.ok().body(personaService.buscarPorCedulaLike(cedula));
    }




/*
    @Autowired
    private MotorService personaService;

    @GetMapping("/persona/obtenerTodos")
    public List<Color> obtenerTodos() {
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