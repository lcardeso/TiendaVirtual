package com.example.demo.web;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.PersonaDTO;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farmacia/persona")

public class PersonaController {

    @Autowired
    public PersonaService personaService;

    @GetMapping("/listar")
    public ResponseEntity<List<PersonaDTO>> obtener() {
        return ResponseEntity.ok().body(personaService.obtener());
    }

 /*   *//**ERROR*//*
    @GetMapping("/listarTipo")
    public ResponseEntity<List<?>> listarPorTipo(@RequestParam String tipo){
        return ResponseEntity.ok().body(personaService.listar(tipo));
    }*/
}