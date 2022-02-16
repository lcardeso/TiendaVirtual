package com.example.demo.DTO;

import java.io.Serializable;

public class BusquedaLikeUsuario implements Serializable {

    private String nombre;

    public BusquedaLikeUsuario() {
    }

    public BusquedaLikeUsuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "BusquedaLikeUsuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
