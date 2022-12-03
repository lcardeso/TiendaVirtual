package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActualizarMetPagoVentaDTO implements Serializable {

    private Long idVenta;
    private Long idMetodoPago;
    private Integer plazoFinanciacion;
    private Double cuotaInicial;

}
