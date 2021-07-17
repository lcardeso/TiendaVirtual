package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class UsuarioDTO implements Serializable {

    private Long id;
    private Integer edad;
    private String nombre;
    @JsonProperty("primApellido")
    private String primerApellido;
    @JsonProperty("segApellido")
    private String segundoApellido;
    private String sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime fechNac;
    private Long direccionId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, Integer edad, String nombre, String primerApellido, String segundoApellido, String sexo, LocalDateTime fechNac, Long direccionId) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
        this.fechNac = fechNac;
        this.direccionId = direccionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechNac=" + fechNac +
                ", direccionId=" + direccionId +
                '}';
    }
}