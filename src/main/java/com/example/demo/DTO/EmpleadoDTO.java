package com.example.demo.DTO;

import com.example.demo.domain.Direccion;
import com.example.demo.domain.Titulo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmpleadoDTO implements Serializable {

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
    private Double salario;
    private String cargoEmpleado;
    private Direccion direccion;
    private Titulo titulo;


}