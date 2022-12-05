package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_AUTOMOVIL;

@Service
@Transactional
public class ListaNegraPersonaService {

  /*  @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private AutomovilRepository automovilRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    //Comprobar si la persona es morosa o no para determinar si se le puede vender un coche o no
    public ResponseDto perMorosa(String cedula) {
        Optional<Persona> persona = personaRepository.findByCedula(cedula);
        if (persona.isPresent()) {
            long cantCancelaciones = ventaRepository.findByCantCancelaciones(cedula);
            if (cantCancelaciones > 2) {
                return new ResponseDto().status("200").message("La persona es MOROSA");
            }
        }
        return new ResponseDto().status("200").message("La persona NO es MOROSA");
    }

    //Listado de las personas morosas
    public PersonaMorosaDTO obtener() {
        List<Persona> perMorosa = ventaRepository.findByCantPersonasMorosas();
        Integer total = perMorosa.size();
        List<PersonaDTO> listaMapper = mapperUtils.mapeoListaObjetoObjeto(perMorosa, PersonaDTO.class);
        return new PersonaMorosaDTO().total(total).personas(listaMapper);
    }*/



}





