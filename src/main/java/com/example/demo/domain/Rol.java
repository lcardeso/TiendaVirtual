package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "rol", schema = "farmacia")
public class Rol implements Serializable {

    @NotNull
    @OneToMany(mappedBy = "rol")
    private List<PermisosAsociados> permisosAsociados;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;


}