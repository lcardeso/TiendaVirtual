package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;


public class AlumnoDTO extends PersonaDTO implements Serializable {

    private String numExpediente;


    public AlumnoDTO() {
    }

    public AlumnoDTO(Integer edad, String dni, String nombre, String primApellido, String segApellido, String sexo, LocalDateTime fechNac, Long direccionId, Long grupoId, String numExpediente) {
        super(edad, dni, nombre, primApellido, segApellido, sexo, fechNac, direccionId, grupoId);
        this.numExpediente = numExpediente;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }
}