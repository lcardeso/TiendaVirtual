package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario", schema = "estudio")
public class Usuario implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_grupo_fk", referencedColumnName = "id_grupo")
    private Grupo grupo;

    @OneToOne
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "primer_apellido")
    private String primApellido;

    @NotNull
    @Column(name = "segundo_apellido")
    private String segApellido;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechNac;

    @NotNull
    @Column(name = "edad")
    private Integer edad;

    @NotNull
    @Column(name = "sexo", length = '1')
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimApellido() {
        return primApellido;
    }

    public void setPrimApellido(String primApellido) {
        this.primApellido = primApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public LocalDateTime getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDateTime fechNac) {
        this.fechNac = fechNac;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", direccion=" + direccion +
                ", nombre='" + nombre + '\'' +
                ", primApellido='" + primApellido + '\'' +
                ", segApellido='" + segApellido + '\'' +
                ", fechNac=" + fechNac +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                '}';
    }


    public Usuario id(Long id) {
        this.id = id;
        return this;
    }

    public Usuario grupo(Grupo grupo) {
        this.grupo = grupo;
        return this;
    }

    public Usuario direccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Usuario nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Usuario primApellido(String primApellido) {
        this.primApellido = primApellido;
        return this;
    }

    public Usuario segApellido(String segApellido) {
        this.segApellido = segApellido;
        return this;
    }

    public Usuario fechNac(LocalDateTime fechNac) {
        this.fechNac = fechNac;
        return this;
    }

    public Usuario edad(Integer edad) {
        this.edad = edad;        return this;
    }

    public Usuario sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }
}