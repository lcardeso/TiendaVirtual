package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "solicitud_compra", schema = "farmacia")
public class SolicitudCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

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

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NotNull
    @Column(name = "estado")
    private String estado;


    public SolicitudCompra id(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudCompra farmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
        return this;
    }

    public SolicitudCompra laboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
        return this;
    }

    public SolicitudCompra medicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        return this;
    }

    public SolicitudCompra cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public SolicitudCompra fecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public SolicitudCompra estado(String estado) {
        this.estado = estado;
        return this;
    }
}