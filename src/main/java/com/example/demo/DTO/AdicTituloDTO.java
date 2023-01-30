package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdicTituloDTO implements Serializable {

    private String cedula;
    private List<TituloDTO> titulos;




}