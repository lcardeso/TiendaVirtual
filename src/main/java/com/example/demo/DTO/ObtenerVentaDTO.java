package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ObtenerVentaDTO implements Serializable {

    private String matriculaAutomovil;
    private String cedulaPersona;
    private String tipoPago;
    private String precioVenta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVenta;


}
