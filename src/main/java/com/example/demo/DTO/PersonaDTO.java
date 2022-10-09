package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class PersonaDTO implements Serializable {

    private Integer edad;
    private String dni;
    private String nombre;
    private String primApellido;
    private String segApellido;
    private String sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechNac;
    private Long direccionId;
    private Long grupoId;

    public PersonaDTO() {
    }

    public PersonaDTO(Integer edad, String dni, String nombre, String primApellido, String segApellido, String sexo, LocalDateTime fechNac, Long direccionId, Long grupoId) {
        this.edad = edad;
        this.dni = dni;
        this.nombre = nombre;
        this.primApellido = primApellido;
        this.segApellido = segApellido;
        this.sexo = sexo;
        this.fechNac = fechNac;
        this.direccionId = direccionId;
        this.grupoId = grupoId;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDateTime fechNac) {
        this.fechNac = fechNac;
    }

    public Long getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Long direccionId) {
        this.direccionId = direccionId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "edad=" + edad +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primApellido='" + primApellido + '\'' +
                ", segApellido='" + segApellido + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechNac=" + fechNac +
                ", direccionId=" + direccionId +
                ", grupoId=" + grupoId +
                '}';
    }
}