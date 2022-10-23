package com.example.demo.DTO;

import com.example.demo.domain.Estado;
import com.example.demo.domain.Venta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AutomovilDTO implements Serializable {
    private String matricula;
    private String foto;
    private String modelo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime primMatriculacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaFabricacion;
    private String fabricante;
    private String categoria;
    private String cilindraje;
    private String color;
    private Long potencia;
    private String estado;


}