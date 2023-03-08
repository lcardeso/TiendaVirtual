package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.LugarStock;
import com.example.demo.domain.Persona;
import com.example.demo.repository.FarmaciaRepository;
import com.example.demo.repository.LugarStockRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_FARMACIA;

@Service
@Transactional
public class FarmaciaService {

    @Autowired
    FarmaciaRepository farmaciaRepository;
    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    LugarStockRepository lugarStockRepository;

    //Listar Empleados
    public List<PersonaDTO> listarEmpleados(Long idFarmacia) {
        Optional<Farmacia> farmaciaOpt = farmaciaRepository.findById(idFarmacia);
        if (farmaciaOpt.isPresent()) {
            List<Persona> listPersona = farmaciaOpt.get().getPersonas();
            return mapperUtils.mapeoListaObjetoObjeto(listPersona, PersonaDTO.class);
        }
        return null;
    }


    //Listar lugares Stock
    public List<LugarStockDTO> listarLugarStock(Long idFarmacia) {
        Optional<Farmacia> farmaciaOpt = farmaciaRepository.findById(idFarmacia);
        List<LugarStock> lugarStocks = farmaciaOpt.get().getLugarStocks();
        return mapperUtils.mapeoListaObjetoObjeto(lugarStocks, LugarStockDTO.class, MAPPER_FARMACIA);
    }

}









