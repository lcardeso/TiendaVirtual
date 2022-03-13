package com.example.demo.DTO;

import java.io.Serializable;

public class BusquedaLikeDireccionCP implements Serializable {

    private String codigoPostal;

    public BusquedaLikeDireccionCP() {
    }

    public BusquedaLikeDireccionCP(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "BusquedaLikeDireccionCP{" +
                "codigoPostal='" + codigoPostal + '\'' +
                '}';
    }
}
