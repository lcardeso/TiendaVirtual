package com.example.demo.DTO;

import java.io.Serializable;


public class BusquedaPersonaEdadDTO implements Serializable {

    private Integer edad;

    public BusquedaPersonaEdadDTO() {
    }

    public BusquedaPersonaEdadDTO(Integer edad) {
        this.edad = edad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "BusquedaUsuarioEdadDTO{" +
                "edad=" + edad +
                '}';
    }
}