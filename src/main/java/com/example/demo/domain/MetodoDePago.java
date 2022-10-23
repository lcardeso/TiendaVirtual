package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "metodo_pago", schema = "concesionario")
public class MetodoDePago implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_pago_fin_FK", referencedColumnName = "id_pago_financiado")
    private PagoFinanciado pagoFinanciado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long id;

    @NotNull
    @Column(name = "tipo_pago")
    private String tipoPago;


}