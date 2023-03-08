package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "motivo_baja", schema = "farmacia")
public class MotivoBajaStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo")
    private Long id;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo", length = 2)
    private String codigo;

    @NotNull
    @Column(name = "estado")
    private String estado;


    public MotivoBajaStock id(Long id) {
        this.id = id;
        return this;
    }

    public MotivoBajaStock descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public MotivoBajaStock codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public MotivoBajaStock estado(String estado) {
        this.estado = estado;
        return this;
    }
}