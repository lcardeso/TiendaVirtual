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

 /*   @Autowired
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

    @GetMapping("/buscarPorCedula")
    public ResponseEntity<PersonaDTO> buscarPorCedula(@RequestParam String cedula) {
        return ResponseEntity.ok().body(personaService.buscarPorCedula(cedula));
    }

    @GetMapping("/buscarPorCedulaLike")
    public ResponseEntity<List<String>> buscarCedulaLike( @RequestParam String cedula) {
        return ResponseEntity.ok().body(personaService.buscarPorCedulaLike(cedula));
    }
*/

}