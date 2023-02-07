package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PersonaDTO implements Serializable {

    private String cedula;
    private String nombre;
    private String primApellido;
    private String segApellido;
    private String sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaNacimiento;
    private Integer telefono;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaIngreso;
    private DireccionDTO direccion;
    private List<TituloDTO> titulos;
    private String nombFarmacia;
    private Long idFarmacia;

}