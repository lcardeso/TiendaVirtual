package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private String cedula;
    private String rolDescripcion;
    private String usuario;
    private String contrasenna;
    private Boolean activo;




}