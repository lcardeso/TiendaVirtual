package com.example.demo.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class DireccionDTO implements Serializable {

    private String calle;
    private Integer numero;
    private Integer piso;
    private String puerta;
    private Integer codigoPostal;
    private String municipio;
    private String provincia;



}