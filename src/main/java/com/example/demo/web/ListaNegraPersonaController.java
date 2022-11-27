package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.ListaNegraPersonaService;
import com.example.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/concesionario")
public class ListaNegraPersonaController {

    @Autowired
    private ListaNegraPersonaService listaNegraPersonaService;

    @GetMapping("/listaNegra")
    public ResponseEntity<ResponseDto> listaNegra(String cedula){
        return ResponseEntity.ok().body(listaNegraPersonaService.perMorosa(cedula));
    }

    @GetMapping("/obtenerListaNegra")
    public ResponseEntity<PersonaMorosaDTO> obtenerListaNegra(){
        return ResponseEntity.ok().body(listaNegraPersonaService.obtener());
    }

}


