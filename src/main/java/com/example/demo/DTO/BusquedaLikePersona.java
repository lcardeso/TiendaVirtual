package com.example.demo.DTO;

import java.io.Serializable;

public class BusquedaLikePersona implements Serializable {

    private String nombre;

    public BusquedaLikePersona() {
    }

    public BusquedaLikePersona(String nombre) {
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
