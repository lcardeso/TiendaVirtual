package com.example.demo.service;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.PersonaDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Persona;
import com.example.demo.repository.FarmaciaRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FarmaciaService {

    @Autowired
    FarmaciaRepository farmaciaRepository;
    @Autowired
    MapperUtils mapperUtils;

    public List<PersonaDTO> listarEmpleados(Long idFarmacia) {
        Optional<Farmacia> farmaciaOpt = farmaciaRepository.findById(idFarmacia);
        if (farmaciaOpt.isPresent()) {
            List<Persona> listPersona = farmaciaOpt.get().getPersonas();
            //listPersona.forEach(persona -> persona instanceof Empleado);
            return mapperUtils.mapeoListaObjetoObjeto(listPersona, PersonaDTO.class);
        }
        // return System.out.println("La lista esta vacia");
        return null;
    }
}









