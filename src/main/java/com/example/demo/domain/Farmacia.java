package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "farmacia", schema = "farmacia")
public class Farmacia implements Serializable {

    @OneToMany
    private List<Persona> personas;

    @OneToMany
    private List<SolicitudCompra> solicitudes;

    @OneToMany
    private List<LugarStock> lugarStocks;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_farmacia")
    private Long id;

    @NotNull
    @Column(name = "num_sucursal", unique = true)
    private Integer numSucursal;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "telefono", length = 9)
    private Integer telefono;



}