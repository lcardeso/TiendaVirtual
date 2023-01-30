package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/empleado")

public class EmpleadoController {

    @Autowired
    public EmpleadoService empleadoService;


    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        return ResponseEntity.ok().body(empleadoService.listarEmpleados());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok().body(empleadoService.adicionar(empleadoDTO));
    }

    @GetMapping("/buscarCedula")
    public ResponseEntity<EmpleadoDTO> buscarPorCedula(@RequestParam String cedula) {
        return ResponseEntity.ok().body(empleadoService.buscarPorCedula(cedula));
    }

    @GetMapping("/buscarCedulaLike")
    public ResponseEntity<List<String>> buscarPorCedulaLike(@RequestParam  String cedula) {
        return ResponseEntity.ok().body(empleadoService.buscarPorCedulaLike(cedula));
    }

    @GetMapping("/buscarCargo")
    public ResponseEntity<List<EmpleadoDTO>> buscarCargo(@RequestParam String cargo) {
        return ResponseEntity.ok().body(empleadoService.buscarCargo(cargo));
    }

    @PostMapping("/modDireccion")
    public ResponseEntity<ResponseDto> modDireccion(@Valid @RequestBody ModDireccionDTO modDireccionDTO) {
        return ResponseEntity.ok().body(empleadoService.modDireccion(modDireccionDTO));
    }

    @PostMapping("/modTitulo")
    public ResponseEntity<ResponseDto> modTitulo(@Valid @RequestBody ModTituloDTO modTituloDTO) {
        return ResponseEntity.ok().body(empleadoService.modTitulo(modTituloDTO));
    }

    @PostMapping("/adicTitulo")
    public ResponseEntity<ResponseDto> adicTitulo(@Valid @RequestBody AdicTituloDTO adicTituloDTO) {
        return ResponseEntity.ok().body(empleadoService.adicTitulo(adicTituloDTO));
    }

    @GetMapping("/buscarNombre")
    public ResponseEntity<EmpleadoDTO> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok().body(empleadoService.buscarPorNombre(nombre));
    }

    @GetMapping("/buscarNombreLike")
    public ResponseEntity<List<String>> buscarPorNombreLike(String nombre) {
        return ResponseEntity.ok().body(empleadoService.buscarPorNombreLike(nombre));
    }


}