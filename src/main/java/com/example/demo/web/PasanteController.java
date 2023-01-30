package com.example.demo.web;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.PasanteDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.PasantiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/pasante")

public class PasanteController {
/*

    @Autowired
    public PasantiaService pasantiaService;


    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        return ResponseEntity.ok().body(PasantiaService.listarPasantes());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody PasanteDTO pasanteDTO) {
        return ResponseEntity.ok().body(pasantiaService.adicionar(pasanteDTO));
    }

    @GetMapping("/buscarCedula")
    public ResponseEntity<EmpleadoDTO> buscarPorCedula(String cedula) {
        return ResponseEntity.ok().body(empleadoService.buscarPorCedula(cedula));
    }

    @GetMapping("/buscarCedulaLike")
    public ResponseEntity<List<String>> buscarPorCedulaLike(String cedula) {
        return ResponseEntity.ok().body(empleadoService.buscarPorCedulaLike(cedula));
    }

    @GetMapping("/buscarCargo")
    public ResponseEntity<List<EmpleadoDTO>> buscarCargo(@Valid @RequestParam String cargo) {
        return ResponseEntity.ok().body(empleadoService.buscarCargo(cargo));
    }

    @PostMapping("/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok().body(empleadoService.modificar(empleadoDTO));
    }

    @GetMapping("/buscarNombre")
    public ResponseEntity<EmpleadoDTO> buscarPorNombre(String nombre) {
        return ResponseEntity.ok().body(empleadoService.buscarPorNombre(nombre));
    }

    @GetMapping("/buscarNombreLike")
    public ResponseEntity<List<String>> buscarPorNombreLike(String nombre) {
        return ResponseEntity.ok().body(empleadoService.buscarPorNombreLike(nombre));
    }
*/


}