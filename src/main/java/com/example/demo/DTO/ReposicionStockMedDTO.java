package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReposicionStockMedDTO implements Serializable {

    private Long idLugarStock;
    private Long idMedicamento;
    private Integer cantidad;
}