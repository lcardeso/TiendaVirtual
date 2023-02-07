package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name = "persona", schema = "farmacia")
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Titulo> titulos;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia_FK", referencedColumnName = "id_farmacia")
    private Farmacia farmacia;

    @NotNull
    @Column(name = "cedula", unique = true, length = 9)
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

    @NotNull
    @Column(name = "permanencia", length = 1)
    private String permanencia;

    public Persona id(Long id) {
        this.id = id;
        return this;
    }

    public Persona titulos(List<Titulo> titulos) {
        this.titulos = titulos;
        return this;
    }

    public Persona direccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Persona farmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
        return this;
    }

    public Persona cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public Persona nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Persona primApellido(String primApellido) {
        this.primApellido = primApellido;
        return this;
    }

    public Persona segApellido(String segApellido) {
        this.segApellido = segApellido;
        return this;
    }

    public Persona sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public Persona fechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Persona telefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public Persona fechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public Persona permanencia(String permanencia) {
        this.permanencia = permanencia;
        return this;
    }
}