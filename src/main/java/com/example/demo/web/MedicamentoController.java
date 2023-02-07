package com.example.demo.web;

import com.example.demo.DTO.MedicamentoDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/farmacia/medicamento")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;


    @PostMapping("/vendMedicamento")
    public ResponseEntity<ResponseDto> vendMedicamento(@Valid @RequestBody MedicamentoDTO medicamentoDTO){
        return ResponseEntity.ok().body(medicamentoService.venderMed(medicamentoDTO));
    }

    /*@PostMapping("/comprarMedicamento")
    public ResponseEntity<ResponseDto> compMedicamento(@Valid @RequestBody )*/



}


