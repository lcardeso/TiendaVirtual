package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "cliente", schema = "usuario")
public class Cliente extends Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

 /*   @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    public Direccion direccion;*/

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "prim_apellido")
    private Integer primApellido;


    @Column(name = "email")
    private String email;

    @Column(name = "telefono", length = 9)
    private Integer telefono;

    @NotNull
    @Column(name = "contrasenna")
    private Integer contrasena;


    public Cliente id(Long id) {
        this.id = id;
        return this;
    }


    public Cliente nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Cliente primApellido(Integer primApellido) {
        this.primApellido = primApellido;
        return this;
    }

    public Cliente email(String email) {
        this.email = email;
        return this;
    }

    public Cliente telefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public Cliente contrasena(Integer contrasena) {
        this.contrasena = contrasena;
        return this;
    }
}