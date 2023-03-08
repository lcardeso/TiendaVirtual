package com.example.demo.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class EmpleadoDTO extends PersonaDTO implements Serializable {

    private ResponseDto responseDto;
    private Double salario;
    private String cargo;


}