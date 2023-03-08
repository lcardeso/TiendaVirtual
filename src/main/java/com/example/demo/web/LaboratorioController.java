package com.example.demo.web;

import com.example.demo.DTO.LaboratorioDTO;
import com.example.demo.DTO.ListarLaboratorioDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Laboratorio;
import com.example.demo.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/laboratorio")
public class LaboratorioController {

    @Autowired
    LaboratorioService laboratorioService;

    @GetMapping("/listar")
    public ResponseEntity<List<ListarLaboratorioDTO>> listar(){
        return ResponseEntity.ok().body(laboratorioService.listar());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody LaboratorioDTO laboratorioDTO){
        return ResponseEntity.ok().body(laboratorioService.adicionar(laboratorioDTO));
    }

    @GetMapping("/modTelefono")
    public ResponseEntity<ResponseDto> modTelefono(@RequestParam Long id, @RequestParam Integer telefono){
        return ResponseEntity.ok().body(laboratorioService.modTelefono(id, telefono));
    }

    @GetMapping("/eliminar")
    public ResponseEntity<ResponseDto> eliminar(@RequestParam Long idLaboratorio){
        return ResponseEntity.ok().body(laboratorioService.eliminar(idLaboratorio));
    }


}


