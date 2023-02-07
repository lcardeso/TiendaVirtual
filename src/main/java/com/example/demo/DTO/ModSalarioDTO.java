package com.example.demo.DTO;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

@Data
public class ModSalarioDTO implements Serializable {

    private Long idEmpleado;
    private Double salarioNuevo;
    private String resolucion;


}