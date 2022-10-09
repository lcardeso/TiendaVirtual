package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Persona;
import com.example.demo.sevice.AlumnoService;
import com.example.demo.sevice.DireccionService;
import com.example.demo.sevice.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudio")

public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // Busca un Alumno por nombre
    @PostMapping(value = "/alumno/buscar/Like/nombre")
    public ResponseEntity<List<Alumno>> usuarioXNombre(@Valid @RequestBody BusquedaLikePersona busquedaLikePersona) {
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
}

