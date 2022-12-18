package com.example.demo.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "empleado", schema = "farmacia")
public class Empleado implements Serializable {

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_titulo_FK", referencedColumnName = "id_titulo")
    private Titulo titulo;

    @OneToOne
    @JoinColumn(name = "id_pasante_FK", referencedColumnName = "id_pasantia")
    private Pasantia pasantia;

    @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "id_farmacia_FK", referencedColumnName = "id_farmacia")
    private Farmacia farmacia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @NotNull
    @Column(name = "cedula", unique = true, length = 7)
    private String cedula;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "prim_apellido")
    private String primApellido;

    @NotNull
    @Column(name = "seg_apellido")
    private String segApellido;

    @NotNull
    @Column(name = "sexo", length = 1)
    private String sexo;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @NotNull
    @Column(name = "telefono", length = 9)
    private Integer telefono;

    @NotNull
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "salario")
    private Double salario ;

    @NotNull
    @Column(name = "cargo_empleado")
    private String cargoEmpleado ;

}