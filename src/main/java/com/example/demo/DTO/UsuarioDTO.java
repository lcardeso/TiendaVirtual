package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class UsuarioDTO implements Serializable {

    private Integer edad;
    private String dni;
    private String nombre;
    @JsonProperty("primApellido")
    private String primerApellido;
    @JsonProperty("segApellido")
    private String segundoApellido;
    private String sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechNac;
    private Long direccionId;
    private Long grupoId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer edad, String dni, String nombre, String primerApellido, String segundoApellido, String sexo, LocalDateTime fechNac, Long direccionId, Long grupoId) {
        this.edad = edad;
        this.dni = dni;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
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

    @JsonProperty("primApellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    @JsonProperty("primApellido")
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @JsonProperty("segApellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    @JsonProperty("segApellido")
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
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
        return "UsuarioDTO{" +
                "edad=" + edad +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechNac=" + fechNac +
                ", direccionId=" + direccionId +
                ", grupoId=" + grupoId +
                '}';
    }
}