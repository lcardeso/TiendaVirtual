package com.example.demo.DTO;

import java.io.Serializable;

/**
 * @Author Alberto Miguel Chirino <alberto.chirino@atos.net>
 * @link http://www.atos.net/
 * @Date 22/7/2021
 * @Version 1.0.0
 */
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
