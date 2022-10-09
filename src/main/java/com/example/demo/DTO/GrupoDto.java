package com.example.demo.DTO;

import com.example.demo.domain.Persona;

import java.io.Serializable;
import java.util.List;

public class GrupoDto implements Serializable {

    private Long id;
    private String nombre;
    private String categoria;
    private List<Persona> personas;



    public GrupoDto() {
    }

    public GrupoDto(Long id, String nombre, String categoria, List<Persona> personas) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.personas = personas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "GrupoDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", personas=" + personas +
                '}';
    }

    public GrupoDto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public GrupoDto categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }


    public GrupoDto id(Long id) {
        this.id = id;
        return this;
    }

    public GrupoDto personas(List<Persona> personas) {
        this.personas = personas;
        return this;
    }
}
