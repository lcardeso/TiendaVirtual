package com.example.demo.DTO;

import com.example.demo.domain.Usuario;

import java.io.Serializable;
import java.util.List;

public class GrupoDto implements Serializable {

    private String nombre;
    private String categoria;
    private List<Usuario> listusuario;


    public GrupoDto() {
    }

    public GrupoDto(String nombre, String categoria, List<Usuario> listusuario) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.listusuario = listusuario;
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

    public List<Usuario> getListusuario() {
        return listusuario;
    }

    public void setListusuario(List<Usuario> listusuario) {
        this.listusuario = listusuario;
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
