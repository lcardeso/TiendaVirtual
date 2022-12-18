package com.example.demo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public  class AutoVendPorMesDTO implements Serializable {

    Integer total = 0;
    List<MedicamentoDTO> autos = new ArrayList<>();


    public AutoVendPorMesDTO total(Integer total) {
        this.total = total;
        return this;
    }

    public AutoVendPorMesDTO autos(List<MedicamentoDTO> autos) {
        this.autos = autos;
        return this;
    }
}