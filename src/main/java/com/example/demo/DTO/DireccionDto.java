package com.example.demo.DTO;

import java.io.Serializable;

public class DireccionDto implements Serializable {

    private Long id;
    private String calle;
    private String numeroApto;
    private String codigoPostal;

    public DireccionDto() {
    }

    public DireccionDto(Long id, String calle, String numeroApto, String codigoPostal) {
        this.id = id;
        this.calle = calle;
        this.numeroApto = numeroApto;
        this.codigoPostal = codigoPostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroApto() {
        return numeroApto;
    }

    public void setNumeroApto(String numeroApto) {
        this.numeroApto = numeroApto;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "DireccionDto{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numeroApto='" + numeroApto + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }
}
