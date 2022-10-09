package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@DiscriminatorValue("P")
@Table(name = "usuario", schema = "estudio")
public class Profesor extends Persona implements Serializable {

    @Column(name = "cat_docente")
    private String catDocente;

    @Column(name = "salario")
    private String salario;

    public String getCatDocente() {
        return catDocente;
    }

    public void setCatDocente(String catDocente) {
        this.catDocente = catDocente;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "catDocente='" + catDocente + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }


    public Profesor catDocente(String catDocente) {
        this.catDocente = catDocente;
        return this;
    }

    public Profesor salario(String salario) {
        this.salario = salario;
        return this;
    }
}