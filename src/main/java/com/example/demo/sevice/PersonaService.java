package com.example.demo.sevice;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_AUTOMOVIL;

@Service
@Transactional
public class PersonaService {
    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private PersonaRepository personaRepository;

    /**
     * ERROR
     **/
    //Listar Personas
    public List<PersonaDTO> obtener() {
        List<Persona> listaPersonas = personaRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(listaPersonas, PersonaDTO.class);
    }


    //Validar Personas
    public ResponseDto validarPersona(PersonaDTO personaDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (personaDTO.getCedula().isEmpty() || personaDTO.getCedula().length() > 9) {
                return respuesta.status("400").message("La cédula no es válida.");
            } else if (personaDTO.getNombre().isEmpty()) {
                return respuesta.status("400").message("El nombre no es válido.");
            } else if (personaDTO.getApellido().isEmpty()) {
                return respuesta.status("400").message("El apellido no es válido.");
            } else if (personaDTO.getTelefono() == null || personaDTO.getTelefono() > 9) {
                return respuesta.status("400").message("El teléfono no es válido.");
            } else if (personaDTO.getSexo().isEmpty()) {
                return respuesta.status("400").message("El sexo no es válido.");
            } else if (personaDTO.getDireccion().isEmpty()) {
                return respuesta.status("400").message("La dirección no es válida.");
            } else {
                return respuesta.status("200").message("La persona ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Adicionar Persona
    public ResponseDto adicionar(PersonaDTO personaDTO) {
        try {
            ResponseDto personaValidada = validarPersona(personaDTO);
            if (!personaValidada.getStatus().equals("200")) {
                return personaValidada;
            } else if (personaRepository.findByCedula(personaDTO.getCedula()).isPresent()) {
                return new ResponseDto().status("400").message("La persona ya existe.");
            }
            Persona persona = mapperUtils.mapeoObjetoObjeto(personaDTO, Persona.class);
            personaRepository.save(persona);
            return new ResponseDto().status("200").message("la persona fue creada exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Modificar Persona
    public ResponseDto modificar(PersonaDTO personaDTO) {
        try {
            Optional<Persona> personaO = personaRepository.findByCedula(personaDTO.getCedula());
            ResponseDto personaValidada = validarPersona(personaDTO);
            if (!personaValidada.getStatus().equals("200")) {
                return personaValidada;
            }
            if (personaO.isPresent()) {
                Persona persona = personaO.get();
                persona.nombre(personaDTO.getNombre()).
                        apellido(personaDTO.getApellido()).
                        telefono(personaDTO.getTelefono()).
                        sexo(personaDTO.getSexo()).
                        direccion(personaDTO.getDireccion());
                personaRepository.save(persona);
                return new ResponseDto().status("200").message("La persona ha sido modificada.");
            }
            return new ResponseDto().status("400").message("Persona no encontrado");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Eliminar Persona a partir del ID
    public ResponseDto eliminar(Long idPersona) {
        try {
            personaRepository.delete(new Persona().id(idPersona));
            return new ResponseDto().status("200").message("Persona eliminada correctamente");
        } catch (
                Exception e) {
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







/*



    //adicionar un profesor
    public ResponseDto adicionar(ProfesorDTO profesorDTO) {
        try {
            ResponseDto profesorValidado = validarProfesor(profesorDTO);
            if (!profesorValidado.getStatus().equals("200")) {
                return profesorValidado;
            }
            Optional<Motor> grupo = grupoRepository.findById(profesorDTO.getGrupoId());
            if (grupo.isEmpty()) {
                return new ResponseDto().status("400").message("El grupo no existe.");
            }
            Profesor profesor = mapperUtils.mapeoObjetoObjeto(profesorDTO, Profesor.class);
            if (profesorDTO.getDireccionId() != null) {
                Optional<Marca> direccion = direccionRepository.findById(profesorDTO.getDireccionId());
                direccion.ifPresent(profesor::setDireccion);
            }
            profesor.setGrupo(grupo.get());
            personaRepository.save(profesor);
            return new ResponseDto().status("200").message("El profesor fue creado exitosamente");
        } catch (Exception e) {
            System.out.println("Excepcion " + e);
            return null;
        }
    }

    //Buscar alumno por nombre
    public List<Profesor> buscar(BusquedaLikePersona nombre) {
        String nombreNormalizado = Normalizer.normalize(nombre.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Profesor> profesorList = profesorRepository.findByNombre(nombreNormalizado);
        if (profesorList.isEmpty()) {
            System.out.println("No hay profesores que mostrar");
        }
        return profesorList;
    }

    //Eliminar alumno a partir de un id establecido
    public ResponseDto eliminar(Long id) {
        Optional<Profesor> profesorO = profesorRepository.findById(id);
        if (profesorO.isPresent()) {
            profesorRepository.delete(profesorO.get());
            return new ResponseDto().status("200").message("Profesor eliminado correctamente");
        }
        return new ResponseDto().status("400").message("Profesor no encontrado");
    }

    //Modificar profesor
    public ResponseDto modificar(ProfesorDTO profesorDTO) {
        Optional<Profesor> profesorOpt = profesorRepository.findByDni(profesorDTO.getDni());
        if (profesorOpt.isPresent()) {
            Profesor profesor = profesorOpt.get();
            profesor.catDocente(profesorDTO.getCatDocente()).
                    salario(profesorDTO.getSalario()).
                    nombre(profesorDTO.getNombre()).
                    primApellido(profesorDTO.getPrimApellido()).
                    segApellido(profesorDTO.getSegApellido()).
                    sexo(profesorDTO.getSexo()).
                    fechNac(profesorDTO.getFechNac()).
                    edad(profesor.getEdad());
            profesorRepository.save(profesor);
            return new ResponseDto().status("200").message("El profesor ha sido modificado.");
        }
        return new ResponseDto().status("400").message("Profesor no encontrado");
    }
*/


}






