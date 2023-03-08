package com.example.demo.service;

import com.example.demo.DTO.LugarStockDTO;
import com.example.demo.DTO.ReposicionStockMedDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.LugarStock;
import com.example.demo.repository.FarmaciaRepository;
import com.example.demo.repository.LaboratorioRepository;
import com.example.demo.repository.LugarStockRepository;
import com.example.demo.repository.StockMedicamentoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LugarStockService {

    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    StockMedicamentoRepository stockMedicamentoRepository;
    @Autowired
    LugarStockService lugarStockService;
    @Autowired
    LugarStockRepository lugarStockRepository;
    @Autowired
    FarmaciaRepository farmaciaRepository;

    public List<LugarStockDTO> listar() {
        List<LugarStock> lugarStocks = lugarStockRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(lugarStocks, LugarStockDTO.class);
    }

    public ResponseDto validar(LugarStockDTO lugarStockDTO) {
        ResponseDto resp = new ResponseDto();
        if (lugarStockDTO.getNombre().isEmpty()) {
            return resp.status("400").message("El nombre no es válido.");
        } else if (lugarStockDTO.getCategoria().isEmpty()) {
            return resp.status("400").message("La categoría no es válida.");
        } else if (lugarStockDTO.getUbicacion().isEmpty()) {
            return resp.status("400").message("La ubicación no es válida.");
        }
        return resp.status("200").message("El lugar de stock ha sido validado  correctamente");
    }

    public ResponseDto adicionar(LugarStockDTO lugarStockDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            resp = lugarStockService.validar(lugarStockDTO);
            if (!resp.getStatus().equals("200")) {
                return resp;
            }
            Optional<LugarStock> lugarStockOpt = lugarStockRepository.findByNombreAndUbicacion(lugarStockDTO.getNombre(), lugarStockDTO.getUbicacion());
            if (lugarStockOpt.isPresent()) {
                return resp.status("400").message("El lugar de stock ya existe.");
            }
            Optional<Farmacia> farmaciaOpt = farmaciaRepository.findById(lugarStockDTO.getIdFarmacia());
            if (farmaciaOpt.isEmpty()) {
                return resp.status("400").message("La farmacia no existe.");
            }
            LugarStock lugarStock = mapperUtils.mapeoObjetoObjeto(lugarStockDTO, LugarStock.class);
            lugarStock.farmacia(farmaciaOpt.get());
            lugarStockRepository.save(lugarStock);
            return resp.status("200").message("El lugar de stock ha sido creado correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salió mal " + e.getMessage());
        }
    }

    public ResponseDto eliminar(Long idLugarStock) {
        ResponseDto resp = new ResponseDto();
        try {
            lugarStockRepository.delete(new LugarStock().id(idLugarStock));
            return resp.status("200").message("El lugar de stock ha sido eliminado correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salió mal " + e.getMessage());
        }
    }

    public ResponseDto modNombre(Long id, String nombre) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<LugarStock> lugarStockOpt = lugarStockRepository.findById(id);
            if (lugarStockOpt.isEmpty()) {
                return resp.status("400").message("El lugar de Stock no existe.");
            }
            LugarStock lugarStock = lugarStockOpt.get();
            lugarStock.nombre(nombre);
            return resp.status("200").message("El nombre del lugar de stock ha sido modificado correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salió mal " + e.getMessage());
        }
    }

    public ResponseDto modCategoria(Long id, String categoria) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<LugarStock> lugarStockOpt = lugarStockRepository.findById(id);
            if (lugarStockOpt.isEmpty()) {
                return resp.status("400").message("El lugar de stock no existe.");
            }
            LugarStock lugarStock = lugarStockOpt.get();
            lugarStock.categoria(categoria);
            return resp.status("200").message("La categoría del lugar de stock ha sido modificada correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salió mal " + e.getMessage());
        }
    }

    public List<String> buscarPorNombreLike(String nombre) {
        try {
            return lugarStockRepository.findByNombreLike(nombre);
        } catch (Exception e) {
            return null;
        }
    }


}






