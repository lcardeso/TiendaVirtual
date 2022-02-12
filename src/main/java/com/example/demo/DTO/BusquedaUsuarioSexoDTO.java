package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class BusquedaUsuarioSexoDTO implements Serializable {


    private String sexo;

    public BusquedaUsuarioSexoDTO() {
    }

    public BusquedaUsuarioSexoDTO(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "BusquedaUsuarioSexoDTO{" +
                "sexo='" + sexo + '\'' +
                '}';
    }
}
