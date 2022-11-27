package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaService {
    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private PersonaRepository personaRepository;


    //Listar Personas
    public List<PersonaDTO> obtener() {
        List<Persona> listaPersonas = personaRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(listaPersonas, PersonaDTO.class);
    }

    //Validar Personas
    public ResponseDto validarPersona(PersonaDTO personaDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (personaDTO.getCedula().isEmpty()) {
                return respuesta.status("400").message("La cédula no es válida.");
            } else if (personaDTO.getNombre().isEmpty()) {
                return respuesta.status("400").message("El nombre no es válido.");
            } else if (personaDTO.getApellido().isEmpty()) {
                return respuesta.status("400").message("El apellido no es válido.");
            } else if (personaDTO.getTelefono() == null) {
                return respuesta.status("400").message("El teléfono no es válido.");
            } else if (personaDTO.getSexo().isEmpty()) {
                return respuesta.status("400").message("El sexo no es válido.");
            } else if (personaDTO.getDireccion().isEmpty()) {
                return respuesta.status("400").message("La dirección no es válida.");
            } else if (personaDTO.getFechaNacimiento() == null) {
                return respuesta.status("400").message("La fecha de nacimiento no es válida.");
            } else {
                return respuesta.status("200").message("La persona ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Validar Personas Logica
    public ResponseDto validarLogicaPersona(PersonaDTO personaDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (personaDTO.getCedula().length() != 9) {
                return respuesta.status("400").message("La cédula no es válida, debe contener 9 caracteres.");
            } else if (String.valueOf(personaDTO.getTelefono()).length() != 9) {
                return respuesta.status("400").message("El teléfono no es válido, debe contener 9 caracteres.");
            } else if (personaDTO.getFechaNacimiento().isAfter(LocalDateTime.now())) {
                return respuesta.status("400").message("La fecha de nacimiento no es válida.");
            } else if (edadPersona(personaDTO.getFechaNacimiento()) <= 18) {
                return respuesta.status("400").message("La persona no tiene la mayoría de edad.");
            } else {
                return respuesta.status("200").message("La persona ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Adicionar Persona
    public ResponseDto adicionar(PersonaDTO personaDTO) {
        ResponseDto res;
        try {
            res = validarPersona(personaDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            } else {
                res = validarLogicaPersona(personaDTO);
                if (!res.getStatus().equals("200")) {
                    return res;
                }
            }
            if (personaRepository.findByCedula(personaDTO.getCedula()).isPresent()) {
                return new ResponseDto().status("400").message("La persona ya existe.");
            }
            Persona persona = mapperUtils.mapeoObjetoObjeto(personaDTO, Persona.class);
            personaRepository.save(persona);
            return new ResponseDto().status("200").message("La persona fue creada exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Modificar Persona
    public ResponseDto modificar(PersonaDTO personaDTO) {
        try {
            Optional<Persona> personaO = personaRepository.findByCedula(personaDTO.getCedula());
            ResponseDto res;
            res = validarPersona(personaDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            } else {
                res = validarLogicaPersona(personaDTO);
                if (res.getStatus().equals("200")) {
                    Persona persona = personaO.get();
                    persona.nombre(personaDTO.getNombre()).
                            apellido(personaDTO.getApellido()).
                            telefono(personaDTO.getTelefono()).
                            sexo(personaDTO.getSexo()).
                            direccion(personaDTO.getDireccion());
                    personaRepository.save(persona);
                    return new ResponseDto().status("200").message("La persona ha sido modificada.");
                }
            }
            return res;
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Eliminar Persona a partir del ID
    public ResponseDto eliminar(Long idPersona) {
        try {
            personaRepository.delete(new Persona().id(idPersona));
            return new ResponseDto().status("200").message("Persona eliminada correctamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }

    }

    //Buscar Persona por Cedula
    public PersonaDTO buscarPorCedula(String cedula) {
        try {
            Optional<Persona> pers = personaRepository.findByCedula(cedula);
            if (pers.isPresent()) {
                return mapperUtils.mapeoObjetoObjeto(pers.get(), PersonaDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //Buscar Persona por Cedula LIKE
    public List<String> buscarPorCedulaLike(String cedula) {
        try {
            return personaRepository.findByCedulaLike(cedula);
        } catch (
                Exception e) {
            return null;
        }
    }

    //Validar edad a partir de la fecha de nacimiento
    public Integer edadPersona(LocalDateTime fechaNac) {
        Integer edad = LocalDateTime.now().getYear() - fechaNac.getYear();
        return edad;


    }


}






