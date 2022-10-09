package com.example.demo.sevice;

import com.example.demo.DTO.*;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Grupo;
import com.example.demo.domain.Profesor;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_ID_UNIDAD;

@Service
@Transactional
public class AlumnoService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private PersonaService personaService;

    //validar un alumno
    public ResponseDto validarAlumno(AlumnoDTO alumno) {
        ResponseDto respuesta = new ResponseDto();
        ResponseDto respValidacion = personaService.validarPersona(alumno);
        if (!respValidacion.getStatus().equals("200")) {
            return respValidacion;
        } else if (alumno.getNumExpediente().isEmpty()) {
            return respuesta.status("400").message("El parámetro número de expediente no es válido.");
        }
        return respuesta.status("200").message("Alumno Validado");
    }

    //adicionar un alumno
    public ResponseDto adicionar(AlumnoDTO alumnoDTO) {
        ResponseDto alumnoValidado = validarAlumno(alumnoDTO);
        if (!alumnoValidado.getStatus().equals("200")) {
            return alumnoValidado;
        }
        Optional<Direccion> direccion = direccionRepository.findById(alumnoDTO.getDireccionId());
        Optional<Grupo> grupo = grupoRepository.findById(alumnoDTO.getGrupoId());
        if (grupo.isEmpty()) {
            return new ResponseDto().status("400").message("El grupo no existe.");
        }
        Alumno alumno = mapperUtils.mapeoObjetoObjeto(alumnoDTO, Alumno.class);
        alumno.setGrupo(grupo.get());
        alumno.setDireccion(direccion.get());
        personaRepository.save(alumno);
        return new ResponseDto().status("200").message("El alumno fue creado exitosamente");
    }

    //Buscar alumno por nombre
    public List<Alumno> buscar(BusquedaLikePersona nombre) {
        String nombreNormalizado = Normalizer.normalize(nombre.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Alumno> alumnoList = alumnoRepository.findByNombre(nombreNormalizado);
        if (alumnoList.isEmpty()) {
            System.out.println("No hay alumnos que mostrar");
        }
        return alumnoList;
    }

    //Eliminar alumno a partir de un id establecido
    public ResponseDto eliminar(Long idAlumno) {
        Optional<Alumno> alumnoO = alumnoRepository.findById(idAlumno);
        if (alumnoO.isPresent()) {
            alumnoRepository.delete(alumnoO.get());
            return new ResponseDto().status("200").message("Alumno eliminado correctamente");
        }
        return new ResponseDto().status("400").message("Alumno no encontrado");
    }

    //Modificar alumno
    public ResponseDto modificar(AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoO = alumnoRepository.findByDni(alumnoDTO.getDni());
        if (alumnoO.isPresent()) {
            Alumno alumno = alumnoO.get();
            alumno.nombre(alumnoDTO.getNombre()).
                    primApellido(alumnoDTO.getPrimApellido()).
                    segApellido(alumnoDTO.getSegApellido()).
                    sexo(alumnoDTO.getSexo()).
                    fechNac(alumnoDTO.getFechNac()).
                    edad(alumnoDTO.getEdad());
            alumnoRepository.save(alumno);
            return new ResponseDto().status("200").message("El alumno ha sido modificado.");
        }
        return new ResponseDto().status("400").message("Alumno no encontrado");
    }

}






