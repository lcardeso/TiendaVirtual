package com.example.demo.DTO;

import com.example.demo.domain.Direccion;
import com.example.demo.domain.LugarStock;
import com.example.demo.domain.StockMedicamento;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class LugarStockDTO implements Serializable {

    private String nombre;
    private String categoria;
    private String ubicacion;
    private Long idFarmacia;

}