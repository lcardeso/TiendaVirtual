package com.example.demo.DTO;

import java.io.Serializable;

public class ActualizarDireccionDto implements Serializable {

    private Long idUsuario;
    private Long idDireccion;


    public ActualizarDireccionDto() {
    }

    public ActualizarDireccionDto(Long idUsuario, Long idDireccion) {
        this.idUsuario = idUsuario;
        this.idDireccion = idDireccion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public String toString() {
        return "ActualizarDireccionDto{" +
                "idUsuario=" + idUsuario +
                ", idDireccion=" + idDireccion +
                '}';
    }
}
