package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "estudio")
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
    private List<Usuario> usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
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
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }


    public Grupo id(Long id) {
        this.id = id;
        return this;
    }

    public Grupo usuario(List<Usuario> usuario) {
        this.usuario = usuario;
        return this;
    }

    public Grupo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Grupo categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }
}
