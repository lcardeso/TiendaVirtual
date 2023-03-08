package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListarLaboratorioDTO implements Serializable {

    private String nombre;
    private Integer telefono;
    private Integer refCastralDir;
    private String calleDir;
    private Integer numDir;
}