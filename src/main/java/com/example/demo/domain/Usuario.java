package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario", schema = "farmacia")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_persona_FK", referencedColumnName = "id_persona")
    private Persona persona;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_rol_FK", referencedColumnName = "id_rol")
    private Rol rol;

    @NotNull
    @Column(name = "usuario")
    private String usuario;

    @NotNull
    @Column(name = "contrasenna")
    private String contrasenna;

    @NotNull
    @Column(name = "activo")
    private Boolean activo;


    public Usuario id(Long id) {
        this.id = id;
        return this;
    }

    public Usuario persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public Usuario rol(Rol rol) {
        this.rol = rol;
        return this;
    }


    public Usuario contrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
        return this;
    }

    public Usuario activo(Boolean activo) {
        this.activo = activo;
        return this;
    }
}