package com.example.demo.web;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.BusquedaLikeDireccionCP;
import com.example.demo.DTO.DireccionDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.sevice.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudio")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping(value = "/direccion/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@RequestBody DireccionDto direccionDto) throws Exception {
        return ResponseEntity.ok().body(direccionService.validarDireccion(direccionDto));
    }

    @GetMapping("/direccion/obtenerTodos")
    public List<Direccion> obtenerTodos() {
        return direccionService.obtenerTodos();
    }

    @DeleteMapping("/direccion/eliminar")
    public ResponseEntity<ResponseDto> eliminar(@RequestParam Long idDireccion) {
        return ResponseEntity.ok().body(direccionService.eliminar(idDireccion));
    }

    @PostMapping(value = "/direccion/buscar/Like/codigoP")
    public ResponseEntity<List<Direccion>> direccionXCodigoP(@Valid @RequestBody BusquedaLikeDireccionCP busquedaLikeDireccion) {
        return ResponseEntity.ok().body(direccionService.buscar(busquedaLikeDireccion.getCodigoPostal()));
    }

    @PostMapping("/direccion/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody DireccionDto direccionDto) {
        return ResponseEntity.ok().body(direccionService.modificar(direccionDto));
    }
}


