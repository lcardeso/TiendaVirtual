package com.example.demo.DTO;

import com.example.demo.domain.Persona;

import java.io.Serializable;
import java.util.List;

public class NombreGrupoDto implements Serializable {

    private String nombre;
    private String categoria;
    private List<Persona> usuarios;


    public NombreGrupoDto() {
    }

    public NombreGrupoDto(String nombre, String categoria, List<Persona> usuarios) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.usuarios = usuarios;
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

    public List<Persona> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Persona> users) {
        this.usuarios = users;
    }

    @Override
    public String toString() {
        return "NombreGrupoDto{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }

    public NombreGrupoDto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public NombreGrupoDto categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }


    public NombreGrupoDto withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public NombreGrupoDto withCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public NombreGrupoDto withUsers(List<Persona> users) {
        this.usuarios = usuarios;
        return this;
    }
}
