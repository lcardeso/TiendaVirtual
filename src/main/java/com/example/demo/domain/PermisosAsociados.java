package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "permisos_asociados", schema = "farmacia")
public class PermisosAsociados extends  Domain implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rol_FK", referencedColumnName = "id_rol")
    private Rol rol;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permisos")
    private Long id;

    @NotNull
    @Column(name = "permiso", unique = true)
    private String permiso;


}