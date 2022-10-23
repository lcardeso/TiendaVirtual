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
    private Long cuotaInicial;

    @NotNull
    @Column(name = "plazo_financiacion")
    private Long plazoFinanciacion;

    @NotNull
    @Column(name = "tipo_financiacion")
    private String tipoFinanciacion;


}