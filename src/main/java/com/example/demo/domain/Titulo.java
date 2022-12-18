package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "titulo", schema = "farmacia")
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulo")
    private Long id;

/*    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_automovil_FK", referencedColumnName = "id_automovil")
    private Farmacia farmacia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_persona_FK", referencedColumnName = "id_persona")
    private Compra persona;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_metodo_pago_FK", referencedColumnName = "id_metodo_pago")
    private Medicamento metodoPago;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pago_fin_FK", referencedColumnName = "id_pago_financiado")
    private Laboratorio pagoFinanciado;*/

    @NotNull
    @Column(name = "nombre_titulo")
    private String nombre;

    @NotNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NotNull
    @Column(name = "institucion")
    private String institucion;

    @NotNull
    @Column(name = "num_registro", unique = true)
    private Integer numRegistro;

    @NotNull
    @Column(name = "especialidad")
    private String especialidad;


    public Titulo id(Long id) {
        this.id = id;
        return this;
    }

    public Titulo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Titulo fecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public Titulo institucion(String institucion) {
        this.institucion = institucion;
        return this;
    }

    public Titulo numRegistro(Integer numRegistro) {
        this.numRegistro = numRegistro;
        return this;
    }

    public Titulo especialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }
}