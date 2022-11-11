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

    @Override
    public String toString() {
        return "Automovil{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", foto='" + foto + '\'' +
                ", modelo='" + modelo + '\'' +
                ", primMatriculacion=" + primMatriculacion +
                ", fechaFabricacion=" + fechaFabricacion +
                ", fabricante='" + fabricante + '\'' +
                ", categoria='" + categoria + '\'' +
                ", cilindraje='" + cilindraje + '\'' +
                ", color='" + color + '\'' +
                ", potencia=" + potencia +
                '}';
    }


    public Automovil ventas(List<Venta> ventas) {
        this.ventas = ventas;
        return this;
    }

    public Automovil estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public Automovil id(Long id) {
        this.id = id;
        return this;
    }

    public Automovil matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public Automovil foto(String foto) {
        this.foto = foto;
        return this;
    }

    public Automovil modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public Automovil primMatriculacion(LocalDateTime primMatriculacion) {
        this.primMatriculacion = primMatriculacion;
        return this;
    }

    public Automovil fechaFabricacion(LocalDateTime fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
        return this;
    }

    public Automovil fabricante(String fabricante) {
        this.fabricante = fabricante;
        return this;
    }

    public Automovil categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public Automovil cilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public Automovil color(String color) {
        this.color = color;
        return this;
    }

    public Automovil potencia(Long potencia) {
        this.potencia = potencia;
        return this;
    }
}