package com.example.demo.DTO;

import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Laboratorio;
import com.example.demo.domain.Medicamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SolicitudCompraDTO implements Serializable {

    private String nombreFarmacia;
    private Long idFarmacia;
    private String nombreLaboratorio;
    private Long idLaboratorio;
    private String nombreMedicamento;
    private Long idMedicamento;
    private Integer cantidad;
}