package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("A")
@Table(name = "usuario", schema = "estudio")
public class Alumno extends Persona implements Serializable {

    @Column(name = "num_expediente")
    private String numExpediente;

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "numExpediente='" + numExpediente + '\'' +
                '}';
    }


    public Alumno numExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
        return this;
    }
}