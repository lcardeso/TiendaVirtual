package com.example.demo.web;

import com.example.demo.DTO.*;
import com.example.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/concesionario/venta")
public class VentaController {

/*    @Autowired
    private VentaService ventaService;

    @GetMapping("/listar")
    public ResponseEntity<List<ObtenerVentaDTO>> obtener() {
        return ResponseEntity.ok().body(ventaService.obtener());
    }

    @GetMapping("/listarVentaPorEstado")
    public ResponseEntity<List<ObtenerVentaDTO>> obtenerVentaPorEstado(@RequestParam String codigo) {
        return ResponseEntity.ok().body(ventaService.obtenerVentaPorEstado(codigo));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@Valid @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok().body(ventaService.adicionar(ventaDTO));
    }

    @GetMapping("/cancelarVenta")
    public ResponseEntity<ResponseDto> cancelarVenta(@RequestParam Long id) {
        return ResponseEntity.ok().body(ventaService.cancelarVenta(id));
    }

    @GetMapping("/ventasPorMes")
    public ResponseEntity<List<ObtenerVentaDTO>> ventasPorMes() {
        return ResponseEntity.ok().body(ventaService.ventasPorMes());
    }

    @PostMapping("/autosVendidosPorMes")
    public ResponseEntity<AutoVendPorMesDTO> autosVendidosPorMes(@RequestBody FechaVentaDTO fechaVentaDTO) {
        return ResponseEntity.ok().body(ventaService.buscarAutosVendPorFecha(fechaVentaDTO.getFecha()));
    }

    @PostMapping("/calcularCuota")
    public ResponseEntity<Double> calcularCuota(@Valid @RequestBody CalculoCuotaDTO calculoCuotaDTO) {
        return ResponseEntity.ok().body(ventaService.cuotaMensual(calculoCuotaDTO));
    }

    @PostMapping("/actualizarMetodoPago")
    public ResponseEntity<ResponseDto> actualizarMetodoPago(@Valid @RequestBody ActualizarMetPagoVentaDTO actualizarMetPagoVentaDTO) {
        return ResponseEntity.ok().body(ventaService.actualizarMetodoPago(actualizarMetPagoVentaDTO));
    }*/

}


