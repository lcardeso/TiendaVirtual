package com.example.demo.DTO;

import com.example.demo.domain.Medicamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class MedicamentoDTO implements Serializable {

    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVencimiento;
    private Double precio;
    private Integer cantidad;



    }
