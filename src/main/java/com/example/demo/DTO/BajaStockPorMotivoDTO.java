package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class BajaStockPorMotivoDTO implements Serializable {

    private Long idLugarStock;
    private Integer cantidad;
    private String codigoMotivo;

    public BajaStockPorMotivoDTO(Long idLugarStock, Integer cantidad, String codigoMotivo) {
        this.idLugarStock = idLugarStock;
        this.cantidad = cantidad;
        this.codigoMotivo = codigoMotivo;
    }

    public BajaStockPorMotivoDTO idLugarStock(Long idLugarStock) {
        this.idLugarStock = idLugarStock;
        return this;
    }

    public BajaStockPorMotivoDTO cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public BajaStockPorMotivoDTO codigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
        return this;
    }
}
