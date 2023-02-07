package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@DiscriminatorValue("E")
@Table(name = "empleado", schema = "farmacia")
public class Empleado extends Persona implements Serializable {

    @NotNull
    @Column(name = "salario")
    private Double salario;

    @Column(name = "resolucion")
    private String resolucion;

    @NotNull
    @Column(name = "cargo")
    private String cargo;


    public Empleado salario(Double salario) {
        this.salario = salario;
        return this;
    }

    public Empleado resolucion(String resolucion) {
        this.resolucion = resolucion;
        return this;
    }

    public Empleado cargo(String cargo) {
        this.cargo = cargo;
        return this;
    }
}