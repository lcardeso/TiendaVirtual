package com.example.demo.DTO;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;


public class ProfesorDTO extends PersonaDTO implements Serializable {

    private String catDocente;
    private String salario;

    public ProfesorDTO() {
    }

    public ProfesorDTO(Integer edad, String dni, String nombre, String primApellido, String segApellido, String sexo, LocalDateTime fechNac, Long direccionId, Long grupoId, String catDocente, String salario) {
        super(edad, dni, nombre, primApellido, segApellido, sexo, fechNac, direccionId, grupoId);
        this.catDocente = catDocente;
        this.salario = salario;
    }

    public String getCatDocente() {
        return catDocente;
    }

    public void setCatDocente(String catDocente) {
        this.catDocente = catDocente;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }
}