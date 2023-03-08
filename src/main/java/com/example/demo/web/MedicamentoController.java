package com.example.demo.web;

import com.example.demo.DTO.DispensarMedDTO;
import com.example.demo.DTO.MedicamentoDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/medicamento")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<MedicamentoDTO>> listar() {
        return ResponseEntity.ok().body(medicamentoService.listar());
    }

    @PostMapping("/dispensar")
    public ResponseEntity<ResponseDto> dispensar(@Valid @RequestBody DispensarMedDTO dispensarMedDTO) {
        return ResponseEntity.ok().body(medicamentoService.dispensar(dispensarMedDTO));
    }


    /*@PostMapping("/comprarMedicamento")
    public ResponseEntity<ResponseDto> compMedicamento(@Valid @RequestBody )*/


}

