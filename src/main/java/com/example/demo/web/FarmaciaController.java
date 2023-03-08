package com.example.demo.web;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.FarmaciaDTO;
import com.example.demo.DTO.LugarStockDTO;
import com.example.demo.DTO.PersonaDTO;
import com.example.demo.domain.LugarStock;
import com.example.demo.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacia/farmacia")

public class FarmaciaController {

    @Autowired
    FarmaciaService farmaciaService;

    @GetMapping("/listarLugarStock")
    public ResponseEntity<List<LugarStockDTO>> listarLugarStock(@RequestParam  Long idFarmacia){
        return ResponseEntity.ok().body(farmaciaService.listarLugarStock(idFarmacia));
    }




}

