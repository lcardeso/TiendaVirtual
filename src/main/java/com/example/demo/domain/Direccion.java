package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "direccion", schema = "estudio")
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion", nullable = false)
    private Long id;

    @Column(name = "calle")
    @NotNull
    private String calle;

    @Column(name = "numero_apto")
    @NotNull
    private String numeroApto;

    @Column(name = "codigo_postal")
    @NotNull
    private String codigoPostal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumeroApto() {
        return numeroApto;
    }

    public void setNumeroApto(String numeroApto) {
        this.numeroApto = numeroApto;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numeroApto='" + numeroApto + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }


    public Direccion id(Long id) {
        this.id = id;
        return this;
    }

    public Direccion calle(String calle) {
        this.calle = calle;
        return this;
    }


    public Direccion codigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public Direccion numeroApto(String numeroApto) {
        this.numeroApto = numeroApto;
        return this;
    }
}
