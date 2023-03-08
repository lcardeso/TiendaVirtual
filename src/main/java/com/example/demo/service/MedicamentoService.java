package com.example.demo.service;

import com.example.demo.Constantes.Constante;
import com.example.demo.DTO.BajaStockPorMotivoDTO;
import com.example.demo.DTO.DispensarMedDTO;
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
import java.util.List;
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
    @Autowired
    StockMedicamentoService stockMedicamentoService;


    //Vender medicamento Baja Stock
    public ResponseDto dispensar(DispensarMedDTO dispensarMedDTO) {
        try {
            return stockMedicamentoService.bajaPorMotivo(new BajaStockPorMotivoDTO(dispensarMedDTO.getIdLugarStock(), dispensarMedDTO.getCantidad(), Constante.DP));
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    public List<MedicamentoDTO> listar() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(medicamentos, MedicamentoDTO.class);
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





