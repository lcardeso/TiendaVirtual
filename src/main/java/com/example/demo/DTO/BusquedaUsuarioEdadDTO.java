package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class BusquedaUsuarioEdadDTO implements Serializable {

    private Integer edad;

    public BusquedaUsuarioEdadDTO() {
    }

    public BusquedaUsuarioEdadDTO(Integer edad) {
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