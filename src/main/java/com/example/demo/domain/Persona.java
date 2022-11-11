package com.example.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "persona", schema = "concesionario")
public class Persona implements Serializable {

    @OneToMany(mappedBy = "persona")
    private List<Venta> ventas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @NotNull
    @Column(name = "cedula", unique = true, length = 7)
    private String cedula;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "apellido")
    private String apellido;

    @NotNull
    @Column(name = "telefono", length = 9)
    private Integer telefono;

    @NotNull
    @Column(name = "sexo", length = 1)
    private String sexo;

    @NotNull
    @Column(name = "direccion")
    private String direccion;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", sexo='" + sexo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }


    public Persona id(Long id) {
        this.id = id;
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

    public Persona apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public Persona telefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public Persona sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public Persona direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Persona fechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }
}