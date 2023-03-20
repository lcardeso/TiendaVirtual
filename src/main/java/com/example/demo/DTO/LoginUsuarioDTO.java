package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginUsuarioDTO implements Serializable {

    private String cedula;
    private String username;
    private String nombreCompleto;
    private String rolDescripcion;
    private List<PermisosAsociadosDTO> permisosAsociadosDTOS;
    private ResponseDto responseDto;



}