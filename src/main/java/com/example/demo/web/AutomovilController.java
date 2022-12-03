package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/concesionario/automovil")

public class AutomovilController {

    @Autowired
    private AutomovilService automovilService;

    @GetMapping("/listar")
    public ResponseEntity<List<AutomovilDTO>> obtener(){
        return ResponseEntity.ok().body(automovilService.obtener());
    }

    @GetMapping ("/autosPorEstado")
    public ResponseEntity<List<AutomovilDTO>> autosPorEstado(@RequestParam Long idEstado){
        return ResponseEntity.ok().body(automovilService.obtenerPorEstado(idEstado));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody AutomovilDTO automovilDTO) {
        return ResponseEntity.ok().body(automovilService.adicionar(automovilDTO));
    }

    @PostMapping("/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody AutomovilDTO automovilDTO) {
        return ResponseEntity.ok().body(automovilService.modificar(automovilDTO));
    }



    @GetMapping("/buscarPorMatricula")
    public ResponseEntity<AutomovilDTO> buscarMatricula(@RequestParam String matricula) {
        return ResponseEntity.ok().body(automovilService.buscarAutomovilMatricula(matricula));
    }

    @GetMapping("/buscarPorMatriculaLike")
    public ResponseEntity<List<String>> buscarMatriculaLike( @RequestParam String matricula) {
        return ResponseEntity.ok().body(automovilService.buscarAutomovilMatriculaLike(matricula));
    }











/*
    @Autowired
    private AutomovilService alumnoService;

    // Busca un Alumno por nombre
    @PostMapping(value = "/alumno/buscar/Like/nombre")
    public ResponseEntity<List<Automovil>> usuarioXNombre(@Valid @RequestBody BusquedaLikePersona busquedaLikePersona) {
        return ResponseEntity.ok().body(alumnoService.buscar(busquedaLikePersona));
    }

    @PostMapping("/alumno/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.ok().body(alumnoService.adicionar(alumnoDTO));
    }

    @DeleteMapping("/alumno/eliminar")
    public ResponseEntity<ResponseDto> delete(@RequestParam Long idAlumno) {
        return ResponseEntity.ok().body(alumnoService.eliminar(idAlumno));
    }

    @PostMapping("/alumno/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.ok().body(alumnoService.modificar(alumnoDTO));
    }

 */
}

