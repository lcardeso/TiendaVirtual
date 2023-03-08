package com.example.demo.service;

import com.example.demo.Constantes.Constante;
import com.example.demo.DTO.*;
import com.example.demo.domain.LugarStock;
import com.example.demo.domain.Medicamento;
import com.example.demo.domain.MotivoBajaStock;
import com.example.demo.domain.StockMedicamento;
import com.example.demo.repository.LugarStockRepository;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.repository.MotivoBajaStockRepository;
import com.example.demo.repository.StockMedicamentoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_STOCK_MED;
import static com.example.demo.Constantes.Constante.RP;

@Service
@Transactional
public class StockMedicamentoService {

    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    StockMedicamentoRepository stockMedicamentoRepository;
    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    LugarStockRepository lugarStockRepository;
    @Autowired
    MotivoBajaStockRepository motivoBajaStockRepository;


    public List<StockMedicamentoDTO> listar() {
        List<StockMedicamento> listaStockMed = stockMedicamentoRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(listaStockMed, StockMedicamentoDTO.class, MAPPER_STOCK_MED);
    }

    public ResponseDto reposicion(ReposicionStockMedDTO reposicionStockMedDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<LugarStock> lugarStockOpt = lugarStockRepository.findById(reposicionStockMedDTO.getIdLugarStock());
            if (lugarStockOpt.isEmpty()) {
                return resp.status("400").message("El lugar de stock no existe.");
            }
            Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(reposicionStockMedDTO.getIdMedicamento());
            if (medicamentoOpt.isEmpty()) {
                return resp.status("400").message("El medicamento no existe.");
            }
            Medicamento medicamento = medicamentoOpt.get();
            LugarStock lugarStock = lugarStockOpt.get();
            List<StockMedicamento> listaStockMedicamento = lugarStock.getStockMedicamentos();
            Optional<StockMedicamento> stockMedicamentoOpt = listaStockMedicamento.stream().filter(stockMedicamento ->
                    stockMedicamento.getMedicamento().getNombre().equals(medicamento.getNombre())).findAny();
            Integer cantExistente = 0;
            if (stockMedicamentoOpt.isPresent()) {
                cantExistente = stockMedicamentoOpt.get().getCantidad();
            }
            if (cantExistente + reposicionStockMedDTO.getCantidad() > 30) {
                return resp.status("400").message("La cantidad supera los límites.");
            }
            Optional<MotivoBajaStock> bajaPorRP = motivoBajaStockRepository.findByCodigo(RP);
            StockMedicamento stockMedicamento = new StockMedicamento().
                    medicamento(medicamento).
                    lugarStock(lugarStock).
                    cantidad(reposicionStockMedDTO.getCantidad()).motivoBajaStock(bajaPorRP.get());
            stockMedicamentoRepository.save(stockMedicamento);
            return resp.status("200").message("La reposición ha sido realizada correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salió mal " + e.getMessage());
        }
    }


    public ResponseDto bajaPorMotivo(BajaStockPorMotivoDTO bajaStockPorMotivoDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<StockMedicamento> stockMedOpt = stockMedicamentoRepository.findById(bajaStockPorMotivoDTO.getIdLugarStock());
            if (stockMedOpt.isEmpty()) {
                return resp.status("400").message("El stock de medicamento no existe.");
            }
            StockMedicamento stockMed = stockMedOpt.get();
            Integer catidadDisponible = stockMed.getCantidad();
            if (catidadDisponible < bajaStockPorMotivoDTO.getCantidad()) {
                return resp.status("400").message("No se puede dar de baja a la cantidad solicitada.");
            }
            Optional<MotivoBajaStock> bajaPorMotivo = motivoBajaStockRepository.findByCodigo(bajaStockPorMotivoDTO.getCodigoMotivo());
            stockMed.motivoBajaStock(bajaPorMotivo.get()).
                    cantidad(catidadDisponible - bajaStockPorMotivoDTO.getCantidad());
            return resp.status("200").message("La baja ha sido realizada correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }

}






