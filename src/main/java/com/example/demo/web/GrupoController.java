package com.example.demo.web;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.NombreGrupoDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Grupo;
import com.example.demo.sevice.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudio")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping(value = "/grupo/adicionar")
    public ResponseEntity<ResponseDto> adicionar(@RequestBody GrupoDto grupoDto) throws Exception {
        return ResponseEntity.ok().body(grupoService.validarGrupo(grupoDto));
    }

    @GetMapping("/grupo/obtenerTodos")
    public List<Grupo> obtenerTodos() {
        return grupoService.obtenerTodos();
    }

    @DeleteMapping("/grupo/eliminar")
    public ResponseEntity<ResponseDto> eliminar(@RequestParam Long idGrupo) {
        return ResponseEntity.ok().body(grupoService.eliminar(idGrupo));
    }

    @PostMapping("/grupo/modificar")
    public ResponseEntity<ResponseDto> modificar(@Valid @RequestBody GrupoDto grupoDto) {
        return ResponseEntity.ok().body(grupoService.modificar(grupoDto));
    }

}


