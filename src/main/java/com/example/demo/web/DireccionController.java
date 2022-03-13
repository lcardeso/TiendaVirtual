package com.example.demo.web;

import com.example.demo.DTO.BusquedaLikeDireccionCP;
import com.example.demo.DTO.BusquedaLikeUsuario;
import com.example.demo.DTO.DireccionDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Usuario;
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
        return direccionService.getAllDireccion();
    }

    @DeleteMapping("/direccion/eliminar")
    public ResponseEntity<ResponseDto> deleteDireccion(@RequestParam Long identdir) {
        return ResponseEntity.ok().body(direccionService.eliminar(identdir));
    }

    @PostMapping(value = "/direccion/buscar/Like/codigoP")
    public ResponseEntity<List<Direccion>> direccionXCodigoP(@Valid @RequestBody BusquedaLikeDireccionCP busquedaLikeDireccion) {
        return ResponseEntity.ok().body(direccionService.buscarDireccion(busquedaLikeDireccion.getCodigoPostal()));
    }

}


