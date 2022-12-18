package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "laboratorio", schema = "farmacia")
public class Laboratorio implements Serializable {

    @OneToMany
    private List<Medicamento> medicamentos;

    @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio")
    private Long id;

    @NotNull
    @Column(name = "telefono", length = 9)
    private Integer telefono;


}