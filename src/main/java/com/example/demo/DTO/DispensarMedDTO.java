package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DispensarMedDTO implements Serializable {

    private Long idLugarStock;
    private String nombre;
    private Double precio;
    private Integer cantidad;



    }
