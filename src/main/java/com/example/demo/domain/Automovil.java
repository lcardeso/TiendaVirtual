package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "automovil", schema = "concesionario")
public class Automovil implements Serializable {

    @OneToMany(mappedBy = "automovil", fetch = FetchType.EAGER)
    private List<Venta> ventas;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_estado_FK", referencedColumnName = "id_estado")
    private Estado estado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_automovil")
    private Long id;

    @NotNull
    @Column(name = "matricula", unique = true)
    private String matricula;

    @NotNull
    @Column(name = "foto")
    private String foto;

    @NotNull
    @Column(name = "modelo")
    private String modelo;

    @NotNull
    @Column(name = "prim_matriculacion")
    private LocalDateTime primMatriculacion;

    @NotNull
    @Column(name = "fecha_fabric")
    private LocalDateTime fechaFabricacion;

    @NotNull
    @Column(name = "fabricante")
    private String fabricante;

    @NotNull
    @Column(name = "categoria")
    private String categoria;

    @NotNull
    @Column(name = "cilindraje")
    private String cilindraje;

    @NotNull
    @Column(name = "color")
    private String color;

    @NotNull
    @Column(name = "potencia")
    private Long potencia;


}