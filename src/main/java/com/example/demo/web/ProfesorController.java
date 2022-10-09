package com.example.demo.web;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.BusquedaLikePersona;
import com.example.demo.DTO.ProfesorDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Profesor;
import com.example.demo.sevice.AlumnoService;
import com.example.demo.sevice.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudio")

public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // Busca un profesor por nombre
    @PostMapping("/profesor/buscar/Like/nombre")
    public ResponseEntity<List<Profesor>> usuarioXNombre(@Valid @RequestBody BusquedaLikePersona busquedaLikePersona) {
        return ResponseEntity.ok().body(profesorService.buscar(busquedaLikePersona));
    }

    @PostMapping("/profesor/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody ProfesorDTO profesorDTO) {
        return ResponseEntity.ok().body(profesorService.adicionar(profesorDTO));
    }

    @DeleteMapping("/profesor/eliminar")
    public ResponseEntity<ResponseDto> delete(@RequestParam Long idProfesor) {
        return ResponseEntity.ok().body(profesorService.eliminar(idProfesor));
    }

    @PostMapping("/profesor/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody ProfesorDTO profesorDTO) {
        return ResponseEntity.ok().body(profesorService.modificar(profesorDTO));
    }
}

