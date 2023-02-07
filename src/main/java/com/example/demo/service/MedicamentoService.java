package com.example.demo.service;

import com.example.demo.DTO.MedicamentoDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Medicamento;
import com.example.demo.domain.StockMedicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.repository.StockMedicamentoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    StockMedicamentoRepository stockMedicamentoRepository;


    /**
     * SI no me quedan medicamentos para vender, la cantidad total es menos que la venta....dodne hago esa validacion???
     */
    //Vender medicamento
    public ResponseDto venderMed(MedicamentoDTO medicamentoDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            stockMedicamentoRepository.updateCantidad(medicamentoDTO.getNombre(), medicamentoDTO.getCantidad());
            return resp.status("200").message("Medicamento vendido");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }



/*    //Comprar medicamento
    public ResponseDto comprarMed(){
        ResponseDto resp = new ResponseDto();
        try {


            return resp.status("200").message("Medicamento vendido");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }*/



}





