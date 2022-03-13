package com.example.demo.DTO;

import com.example.demo.domain.Usuario;

import java.io.Serializable;
import java.util.List;

public class NombreGrupoDto implements Serializable {

    private String nombre;
    private String categoria;


    public NombreGrupoDto() {
    }

    public NombreGrupoDto(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
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


    @Override
    public String toString() {
        return "GrupoDto{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
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

}
