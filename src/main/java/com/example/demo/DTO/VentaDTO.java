package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VentaDTO implements Serializable {

    private Long idAutomovil;
    private Long idPersona;
    private Long idMetodoPago;
    private Double precioVenta;
    private String estadoVenta;
    private Integer plazoFinanciacion;
    private Double cuotaInicial;
    private Boolean financiar;


}
