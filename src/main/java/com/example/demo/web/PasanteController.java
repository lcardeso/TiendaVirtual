package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.PasantiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/farmacia/pasante")

public class PasanteController {


    @Autowired
    public PasantiaService pasantiaService;


    @GetMapping("/listar")
    public ResponseEntity<List<PasanteDTO>> listar() {
        return ResponseEntity.ok().body(pasantiaService.listarPasantes());
    }

    @GetMapping("/listarPasantesPorEstado")
    public ResponseEntity<List<PasanteDTO>> listarPasantesPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok().body(pasantiaService.listarPasantesPorEstado(estado));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody PasanteDTO pasanteDTO) {
        return ResponseEntity.ok().body(pasantiaService.adicionar(pasanteDTO));
    }

    @GetMapping("/buscarCedula")
    public ResponseEntity<PasanteDTO> buscarPorCedula(@RequestParam String cedula) {
        return ResponseEntity.ok().body(pasantiaService.buscarPorCedula(cedula));
    }

    @GetMapping("/buscarCedulaLike")
    public ResponseEntity<List<String>> buscarPorCedulaLike(@RequestParam String cedula) {
        return ResponseEntity.ok().body(pasantiaService.buscarPorCedulaLike(cedula));
    }


    @PostMapping("/modDireccion")
    public ResponseEntity<ResponseDto> modDireccion(@Valid @RequestBody ModDireccionDTO modDireccionDTO) {
        return ResponseEntity.ok().body(pasantiaService.modDireccion(modDireccionDTO));
    }

    @PostMapping("/modTitulo")
    public ResponseEntity<ResponseDto> modTitulo(@Valid @RequestBody ModTituloDTO modTituloDTO) {
        return ResponseEntity.ok().body(pasantiaService.modTitulo(modTituloDTO));
    }

    @PostMapping("/adicTitulo")
    public ResponseEntity<ResponseDto> adicTitulo(@Valid @RequestBody AdicTituloDTO adicTituloDTO) {
        return ResponseEntity.ok().body(pasantiaService.adicTitulo(adicTituloDTO));
    }

    @GetMapping("/buscarNombre")
    public ResponseEntity<PasanteDTO> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok().body(pasantiaService.buscarPorNombre(nombre));
    }

    @GetMapping("/buscarNombreLike")
    public ResponseEntity<List<String>> buscarPorNombreLike(@RequestParam String nombre) {
        return ResponseEntity.ok().body(pasantiaService.buscarPorNombreLike(nombre));
    }

    @PostMapping("/modFechaFin")
    public  ResponseEntity<ResponseDto> modFechaFin(@Valid @RequestBody ModFechaFinPasanteDTO modFechaFinPasanteDTO){
        return ResponseEntity.ok().body(pasantiaService.modFechaFin(modFechaFinPasanteDTO));
    }
    @GetMapping("/darBaja")
    public ResponseEntity<ResponseDto> darBaja(@RequestParam Long idPasante) {
        return ResponseEntity.ok().body(pasantiaService.darBaja(idPasante));
    }


}