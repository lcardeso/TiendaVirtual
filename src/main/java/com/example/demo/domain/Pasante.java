package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@DiscriminatorValue("P")
@Table(name = "pasante", schema = "farmacia")
public class Pasante extends Persona implements Serializable {

  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasantia")
    private Long id;*/

    @NotNull
    @Column(name = "institucion")
    private String institucion;

    @NotNull
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "motivo")
    private String motivo;


    public Pasante institucion(String institucion) {
        this.institucion = institucion;
        return this;
    }

    public Pasante fechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public Pasante motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }
}