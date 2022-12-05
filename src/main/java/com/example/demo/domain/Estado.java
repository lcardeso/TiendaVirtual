package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "estado", schema = "concesionario")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @NotNull
    @Column(name = "codigo", length = 1, unique = true)
    private String codigo;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;


}