package com.example.demo.DTO;

import java.io.Serializable;

public class GrupoDto implements Serializable {

    private String nombre;
    private String categoria;


    public GrupoDto() {
    }

    public GrupoDto(String nombre, String categoria) {
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


    public GrupoDto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public GrupoDto categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }
}
