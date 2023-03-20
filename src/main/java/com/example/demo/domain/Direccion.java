package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "direccion", schema = "farmacia")
public class Direccion extends  Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long id;

    @NotNull
    @Column(name = "ref_castral", unique = true, length = 9)
    private Integer refCastral;

    @NotNull
    @Column(name = "calle")
    private String calle;

    @NotNull
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "piso")
    private Integer piso;

    @Column(name = "numero_puerta")
    private String puerta;

    @NotNull
    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    @NotNull
    @Column(name = "municipio")
    private String municipio;

    @NotNull
    @Column(name = "provincia")
    private String provincia;

    @NotNull
    @Column(name = "tipo")
    private String tipo;


    public Direccion id(Long id) {
        this.id = id;
        return this;
    }

    public Direccion refCastral(Integer refCastral) {
        this.refCastral = refCastral;
        return this;
    }

    public Direccion calle(String calle) {
        this.calle = calle;
        return this;
    }

    public Direccion numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public Direccion piso(Integer piso) {
        this.piso = piso;
        return this;
    }

    public Direccion puerta(String puerta) {
        this.puerta = puerta;
        return this;
    }

    public Direccion codigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public Direccion municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public Direccion provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public Direccion tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
}