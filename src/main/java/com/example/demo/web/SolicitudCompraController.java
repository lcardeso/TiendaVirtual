package com.example.demo.web;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.SolicitudCompraDTO;
import com.example.demo.service.SolicitudCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farmacia/solicitudCompra")
public class SolicitudCompraController {

    @Autowired
    SolicitudCompraService solicitudCompraService;

    @GetMapping("/listar")
    public ResponseEntity<List<SolicitudCompraDTO>> listar() {
        return ResponseEntity.ok().body(solicitudCompraService.listar());
    }

    @PostMapping("/crearSolicitud")
    public ResponseEntity<ResponseDto> crearSolicitud(@Valid @RequestBody SolicitudCompraDTO solicitudCompraDTO) {
        return ResponseEntity.ok().body(solicitudCompraService.crearSolicitud(solicitudCompraDTO));
    }

    /**DUDA, creo que esto lo deberia hacer el Laboratorio*/
    @GetMapping("/cambiarEstado")
    public ResponseEntity<ResponseDto> cambiarEstado(@RequestParam Long idSolicitud, @RequestParam String estadoNew) {
        return ResponseEntity.ok().body(solicitudCompraService.cambiarEstado(idSolicitud, estadoNew));
    }

    @GetMapping("/solicitudesPorEstado")
    public ResponseEntity<List<SolicitudCompraDTO>> solicitudesPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok().body(solicitudCompraService.solicitudesPorEstado(estado));
    }
}


