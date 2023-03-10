package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "motivo", schema = "farmacia")
public class MotivoStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo")
    private Long id;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo", length = 2)
    private String codigo;

    public MotivoStock id(Long id) {
        this.id = id;
        return this;
    }

    public MotivoStock descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public MotivoStock codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }
}