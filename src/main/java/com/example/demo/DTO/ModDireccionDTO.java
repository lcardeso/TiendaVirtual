package com.example.demo.DTO;

import com.example.demo.domain.Direccion;
import lombok.Data;

import java.io.Serializable;

@Data
public class ModDireccionDTO implements Serializable {

    private String cedula;
    private DireccionDTO direccionDTO;


}