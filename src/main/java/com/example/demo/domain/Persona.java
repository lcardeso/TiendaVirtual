package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "persona", schema = "concesionario")
public class Persona implements Serializable {

    @OneToMany(mappedBy = "persona")
    private List<Venta> ventas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @NotNull
    @Column(name = "cedula", unique = true, length = 7)
    private String cedula;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "apellido")
    private String apellido;

    @NotNull
    @Column(name = "telefono", length = 9)
    private Integer telefono;

    @NotNull
    @Column(name = "sexo", length = 1)
    private String sexo;

    @NotNull
    @Column(name = "direccion")
    private String direccion;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

}