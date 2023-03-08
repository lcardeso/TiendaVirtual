package com.example.demo.web;

import com.example.demo.DTO.LugarStockDTO;
import com.example.demo.DTO.MedicamentoDTO;
import com.example.demo.DTO.ReposicionStockMedDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.service.LugarStockService;
import com.example.demo.service.StockMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/lugarStock")
public class LugarStockController {

    @Autowired
    StockMedicamentoService stockMedicamentoService;
    @Autowired
    LugarStockService lugarStockService;

    @GetMapping("/listar")
    public ResponseEntity<List<LugarStockDTO>> listar() {
        return ResponseEntity.ok().body(lugarStockService.listar());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody LugarStockDTO lugarStockDTO) {
        return ResponseEntity.ok().body(lugarStockService.adicionar(lugarStockDTO));
    }

    @GetMapping("/eliminar")
    public ResponseEntity<ResponseDto> eliminar(@RequestParam Long idLugarStock) {
        return ResponseEntity.ok().body(lugarStockService.eliminar(idLugarStock));
    }

    @GetMapping("/modNombre")
    public ResponseEntity<ResponseDto> modNombre(@RequestParam Long id, @RequestParam String nombre) {
        return ResponseEntity.ok().body(lugarStockService.modNombre(id, nombre));
    }

    @GetMapping("/modCategoria")
    public ResponseEntity<ResponseDto> modCategoria(@RequestParam Long id, @RequestParam String categoria) {
        return ResponseEntity.ok().body(lugarStockService.modCategoria(id, categoria));
    }

    @GetMapping("/buscarPorNombreLike")
    public ResponseEntity<List<String>> buscarPorNombreLike(String nombre) {
        return ResponseEntity.ok().body(lugarStockService.buscarPorNombreLike(nombre));
    }


}


