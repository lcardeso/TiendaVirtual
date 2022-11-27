package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AutoVendPorMesDTO implements Serializable {

    Integer total = 0;
    List<AutomovilDTO> autos = new ArrayList<>();


    public AutoVendPorMesDTO total(Integer total) {
        this.total = total;
        return this;
    }

    public AutoVendPorMesDTO autos(List<AutomovilDTO> autos) {
        this.autos = autos;
        return this;
    }
}