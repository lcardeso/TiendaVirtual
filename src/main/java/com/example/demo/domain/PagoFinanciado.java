package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pago_finaciado", schema = "concesionario")
public class PagoFinanciado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_financiado")
    private Long id;

    @NotNull
    @Column(name = "cuota_inicial")
    private Double cuotaInicial;

    @NotNull
    @Column(name = "plazo_financiacion")
    private Integer plazoFinanciacion;

    @NotNull
    @Column(name = "tipo_financiacion")
    private String tipoFinanciacion;


    public PagoFinanciado id(Long id) {
        this.id = id;
        return this;
    }

    public PagoFinanciado cuotaInicial(Double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
        return this;
    }

    public PagoFinanciado plazoFinanciacion(Integer plazoFinanciacion) {
        this.plazoFinanciacion = plazoFinanciacion;
        return this;
    }

    public PagoFinanciado tipoFinanciacion(String tipoFinanciacion) {
        this.tipoFinanciacion = tipoFinanciacion;
        return this;
    }
}