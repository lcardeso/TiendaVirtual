package com.example.demo.sevice;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.BusquedaLikePersona;
import com.example.demo.DTO.ProfesorDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesorService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private PersonaService personaService;

    //validar un profesor
    public ResponseDto validarProfesor(ProfesorDTO profesorDTO) {
        ResponseDto respuesta = new ResponseDto();
        ResponseDto respValidacion = personaService.validarPersona(profesorDTO);
        if (!respValidacion.getStatus().equals("200")) {
            return respValidacion;
        } else if (profesorDTO.getCatDocente().isEmpty()) {
            respuesta.status("400").message("La categoría docente no es válida.");
        } else if (profesorDTO.getSalario().isEmpty()) {
            respuesta.status("400").message("El salario no es válida.");
        }
        respuesta.status("200").message("Profesor Validado");
        return respuesta;
    }

    //adicionar un profesor
    public ResponseDto adicionar(ProfesorDTO profesorDTO) {
        try {
            ResponseDto profesorValidado = validarProfesor(profesorDTO);
            if (!profesorValidado.getStatus().equals("200")) {
                return profesorValidado;
            }
            Optional<Grupo> grupo = grupoRepository.findById(profesorDTO.getGrupoId());
            if (grupo.isEmpty()) {
                return new ResponseDto().status("400").message("El grupo no existe.");
            }
            Profesor profesor = mapperUtils.mapeoObjetoObjeto(profesorDTO, Profesor.class);
            if (profesorDTO.getDireccionId() != null) {
                Optional<Direccion> direccion = direccionRepository.findById(profesorDTO.getDireccionId());
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

}






