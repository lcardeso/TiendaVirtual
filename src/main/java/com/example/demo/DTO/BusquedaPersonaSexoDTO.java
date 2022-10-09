package com.example.demo.DTO;

import java.io.Serializable;


public class BusquedaPersonaSexoDTO implements Serializable {


    private String sexo;

    public BusquedaPersonaSexoDTO() {
    }

    public BusquedaPersonaSexoDTO(String sexo) {
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
