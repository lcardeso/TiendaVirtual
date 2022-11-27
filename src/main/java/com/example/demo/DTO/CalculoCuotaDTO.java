package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class CalculoCuotaDTO implements Serializable {

    private Double precioVenta;
    private Integer plazoFinanciacion;
    private Double cuotaInicial;


}
