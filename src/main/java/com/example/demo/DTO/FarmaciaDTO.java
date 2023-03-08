package com.example.demo.DTO;

import com.example.demo.domain.Direccion;
import com.example.demo.domain.LugarStock;
import com.example.demo.domain.Persona;
import com.example.demo.domain.SolicitudCompra;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FarmaciaDTO implements Serializable {

    private List<PersonaDTO> personas;
    private List<SolicitudCompraDTO> solicitudes;
    private List<LugarStock> lugarStocks;
    private Direccion direccion;
    private Integer numSucursal;
    private String nombre;
    private Integer telefono;

}