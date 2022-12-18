package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "solicitud_compra", schema = "farmacia")
public class SolicitudCompra implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia_FK", referencedColumnName = "id_farmacia")
    private Farmacia farmacia;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_laboratorio_FK", referencedColumnName = "id_laboratorio")
    private Laboratorio laboratorio;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_medicamento_FK", referencedColumnName = "id_medicamento")
    private Medicamento medicamento;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;


}