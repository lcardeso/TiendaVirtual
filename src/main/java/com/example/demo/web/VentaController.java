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

    @Autowired
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


/*    @PostMapping("/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok().body(ventaService.modificar(ventaDTO));
    }*/

    @PostMapping("/calcularCuota")
    public ResponseEntity<Double> calcularCuota(@Valid @RequestBody CalculoCuotaDTO calculoCuotaDTO) {
        return ResponseEntity.ok().body(ventaService.cuotaMensual(calculoCuotaDTO));
    }









/*
    @Autowired
    private ColorService direccionService;

    @PostMapping(value = "/direccion/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@RequestBody DireccionDto direccionDto) throws Exception {
        return ResponseEntity.ok().body(direccionService.validarDireccion(direccionDto));
    }

    @GetMapping("/direccion/obtenerTodos")
    public List<Marca> obtenerTodos() {
        return direccionService.obtenerTodos();
    }

    @DeleteMapping("/direccion/eliminar")
    public ResponseEntity<ResponseDto> eliminar(@RequestParam Long idDireccion) {
        return ResponseEntity.ok().body(direccionService.eliminar(idDireccion));
    }

    @PostMapping(value = "/direccion/buscar/Like/codigoP")
    public ResponseEntity<List<Marca>> direccionXCodigoP(@Valid @RequestBody BusquedaLikeDireccionCP busquedaLikeDireccion) {
        return ResponseEntity.ok().body(direccionService.buscar(busquedaLikeDireccion.getCodigoPostal()));
    }

    @PostMapping("/direccion/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody DireccionDto direccionDto) {
        return ResponseEntity.ok().body(direccionService.modificar(direccionDto));
    }

 */
}


