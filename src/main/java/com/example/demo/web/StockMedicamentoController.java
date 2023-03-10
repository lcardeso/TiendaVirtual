package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.domain.StockMedicamento;
import com.example.demo.service.StockMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/stockMedicamento")
public class StockMedicamentoController {
    @Autowired
    StockMedicamentoService stockMedicamentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<StockMedicamentoDTO>> listar() {
        return ResponseEntity.ok().body(stockMedicamentoService.listar());
    }

    @PostMapping("/reposicion")
    public ResponseEntity<ResponseDto> reposicion(@Valid @RequestBody ReposicionStockMedDTO reposicionStockMedDTO) {
        return ResponseEntity.ok().body(stockMedicamentoService.reposicion(reposicionStockMedDTO));
    }

    @PostMapping("/bajaStockPorMotivo")
    public ResponseEntity<ResponseDto> bajaPorMotivo(@RequestBody BajaStockPorMotivoDTO bajaStockPorMotivoDTO) {
        return ResponseEntity.ok().body(stockMedicamentoService.bajaPorMotivo(bajaStockPorMotivoDTO));
    }
}


