package com.example.demo.service;

import com.example.demo.DTO.PersonaDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Persona;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.TituloRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@Service
@Transactional
public class PersonaService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private TituloService tituloService;
    @Autowired
    private TituloRepository tituloRepository;


    //Listar Personas
    public List<PersonaDTO> obtener() {
        List<Persona> lista = personaRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(lista, PersonaDTO.class);
    }

    //Validar persona
    public ResponseDto validar(PersonaDTO personaDTO) {
        ResponseDto respuesta = new ResponseDto();
        if (personaDTO.getCedula().length() != 9) {
            return respuesta.status("400").message("La cedula no es válida.");
        } else if (personaDTO.getNombre().isEmpty()) {
            return respuesta.status("400").message("El nombre no es válido.");
        } else if (personaDTO.getPrimApellido().isEmpty()) {
            return respuesta.status("400").message("El primer apellido no es válido.");
        } else if (personaDTO.getSegApellido().isEmpty()) {
            return respuesta.status("400").message("El segundo apellido no es válido.");
        } else if (personaDTO.getSexo().length() != 1) {
            return respuesta.status("400").message("El sexo no es válido.");
        } else if (personaDTO.getFechaNacimiento() == null || personaDTO.getFechaNacimiento().isAfter(now()) || edad(personaDTO.getFechaNacimiento()) < 18) {
            return respuesta.status("400").message("La fecha de nacimiento no es válida.");
        } else if (personaDTO.getTelefono() == null || String.valueOf(personaDTO.getTelefono()).length() != 9) {
            return respuesta.status("400").message("El teléfono no es válido.");
        } else if (personaDTO.getDireccion() == null) {
            return respuesta.status("400").message("La dirección no es válida.");
        } else if (personaDTO.getTitulos().isEmpty()) {
            return respuesta.status("400").message("El título no es válido.");
        } else {
            return respuesta.status("200").message("La persona ha sido validada correctamente");
        }
    }

    private Integer edad(LocalDateTime edad) {
        Integer resp = LocalDateTime.now().getYear() - edad.getYear();
        return resp;
    }

}






