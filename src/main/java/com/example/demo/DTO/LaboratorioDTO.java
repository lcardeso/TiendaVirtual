package com.example.demo.DTO;

import com.example.demo.domain.Direccion;
import com.example.demo.domain.LugarStock;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class LaboratorioDTO implements Serializable {

    private String nombre;
    private Integer telefono;
    private DireccionDTO direccionDTO;
    private Integer refCastralDir;
    private String calleDir;
    private Integer numDir;
}