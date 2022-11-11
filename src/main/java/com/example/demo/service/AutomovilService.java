package com.example.demo.service;

import com.example.demo.DTO.AutomovilDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Automovil;
import com.example.demo.domain.Estado;
import com.example.demo.repository.AutomovilRepository;
import com.example.demo.repository.EstadoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_AUTOMOVIL;

@Service
@Transactional
public class AutomovilService {
    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private AutomovilRepository automovilRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    //Listar Automoviles
    public List<AutomovilDTO> obtener() {
        List<Automovil> listaAutos = automovilRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(listaAutos, AutomovilDTO.class, MAPPER_AUTOMOVIL);
    }

    //Buscar Automoviles Por Estado
    public List<AutomovilDTO> obtenerPorEstado(Long idEstado) {
        List<Automovil> autosVendidos = automovilRepository.findByEstado(new Estado().id(idEstado));
        return mapperUtils.mapeoListaObjetoObjeto(autosVendidos, AutomovilDTO.class, MAPPER_AUTOMOVIL);
    }

    //Validar Automoviles
    private Boolean validadMatricula(String matricula) {
        if (matricula.length() == 7) {
            int num = 0;
            int letra = 0;
            for (int i = 0; i < matricula.length(); i++) {
                char c = matricula.charAt(i);
                if (Character.isDigit(c) && num < 4) {
                    num++;
                } else if (Character.isAlphabetic(c) && letra < 3) {
                    letra++;
                }
            }
            return num == 4 && letra == 3;
        }
        return false;

    }

    public ResponseDto validarAutomovil(AutomovilDTO automovilDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (automovilDTO.getMatricula().isEmpty() || !validadMatricula(automovilDTO.getMatricula())) {
                return respuesta.status("400").message("La matrícula no es válida.");
            } else if (automovilDTO.getFoto().isEmpty()) {
                return respuesta.status("400").message("La foto no es válida.");
            } else if (automovilDTO.getModelo().isEmpty()) {
                return respuesta.status("400").message("El modelo no es válido.");
            } else if (automovilDTO.getPrimMatriculacion() == null || automovilDTO.getPrimMatriculacion().isAfter(LocalDateTime.now())) {
                return respuesta.status("400").message("La fecha de matriculación no es válida.");
            } else if (automovilDTO.getFechaFabricacion() == null || automovilDTO.getFechaFabricacion().isAfter(automovilDTO.getPrimMatriculacion())) {
                return respuesta.status("400").message("La fecha de fabricación no es válida.");
            } else if (automovilDTO.getFabricante().isEmpty()) {
                return respuesta.status("400").message("El fabricante no es válido.");
            } else if (automovilDTO.getCategoria().isEmpty()) {
                return respuesta.status("400").message("La categoría no es válida.");
            } else if (automovilDTO.getCilindraje().isEmpty()) {
                return respuesta.status("400").message("El cilindraje no es válido.");
            } else if (automovilDTO.getColor().isEmpty()) {
                return respuesta.status("400").message("El color no es válido.");
            } else if (automovilDTO.getPotencia() == null) {
                return respuesta.status("400").message("La potencia no es válida.");
            } else {
                return respuesta.status("200").message("El automóvil ha sido validado correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Adicionar Automovil
    public ResponseDto adicionar(AutomovilDTO automovilDTO) {
        try {
            ResponseDto automovilValidado = validarAutomovil(automovilDTO);
            if (!automovilValidado.getStatus().equals("200")) {
                return automovilValidado;
            } else if (automovilRepository.findByMatricula(automovilDTO.getMatricula()).isPresent()) {
                return new ResponseDto().status("400").message("La matrícula ya existe.");
            }
            Automovil automovil = mapperUtils.mapeoObjetoObjeto(automovilDTO, Automovil.class);
            estadoRepository.findByCodigo("D").ifPresent(automovil::estado);
            automovilRepository.save(automovil);
            return new ResponseDto().status("200").message("El automóvil fue creado exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Modificar Automovil
    public ResponseDto modificar(AutomovilDTO automovilDTO) {
        try {
            ResponseDto automovilValidado = validarAutomovil(automovilDTO);
            if (!automovilValidado.getStatus().equals("200")) {
                return automovilValidado;
            }
            Optional<Automovil> automovilO = automovilRepository.findByMatricula(automovilDTO.getMatricula());
            if (automovilO.isPresent()) {
                Automovil automovil = automovilO.get();
                automovil.foto(automovilDTO.getFoto()).
                        modelo(automovilDTO.getModelo()).
                        primMatriculacion(automovilDTO.getPrimMatriculacion()).
                        fechaFabricacion(automovilDTO.getFechaFabricacion()).
                        fabricante(automovilDTO.getFabricante()).
                        categoria(automovilDTO.getCategoria()).
                        cilindraje(automovilDTO.getCilindraje()).
                        color(automovilDTO.getColor()).
                        potencia(automovilDTO.getPotencia());
                estadoRepository.findByCodigo(automovilDTO.getEstado()).ifPresent(automovil::estado);
                automovilRepository.save(automovil);
                return new ResponseDto().status("200").message("El automóvil ha sido modificado.");
            }
            return new ResponseDto().status("400").message("Automóvil no encontrado");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Eliminar Automovil a partir del ID
    public ResponseDto eliminar(Long idAutomovil) {
        try {
            automovilRepository.delete(new Automovil().id(idAutomovil));
            return new ResponseDto().status("200").message("Automóvil eliminado correctamente");
        } catch (
                Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Buscar Automovil por Matricula
    public AutomovilDTO buscarAutomovilMatricula(String matricula) {
        try {
            Optional<Automovil> auto = automovilRepository.findByMatricula(matricula);
            if (auto.isPresent()) {
                return mapperUtils.mapeoObjetoObjeto(auto.get(), AutomovilDTO.class, MAPPER_AUTOMOVIL);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //Buscar Automovil por Matricula LIKE
    public List<String> buscarAutomovilMatriculaLike(String matricula) {
        try {
            return automovilRepository.findByMatriculaLike(matricula);
        } catch (Exception e) {
            return null;
        }
    }



}


/*

    //Buscar alumno por nombre
    public List<Automovil> buscar(BusquedaLikePersona nombre) {
        String nombreNormalizado = Normalizer.normalize(nombre.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Automovil> alumnoList = alumnoRepository.findByNombre(nombreNormalizado);
        if (alumnoList.isEmpty()) {
            System.out.println("No hay alumnos que mostrar");
        }
        return alumnoList;
    }

    //Eliminar alumno a partir de un id establecido
    public ResponseDto eliminar(Long idAlumno) {
        Optional<Automovil> alumnoO = alumnoRepository.findById(idAlumno);
        if (alumnoO.isPresent()) {
            alumnoRepository.delete(alumnoO.get());
            return new ResponseDto().status("200").message("Alumno eliminado correctamente");
        }
        return new ResponseDto().status("400").message("Alumno no encontrado");
    }

    //Modificar alumno
    public ResponseDto modificar(AlumnoDTO alumnoDTO) {
        Optional<Automovil> alumnoO = alumnoRepository.findByDni(alumnoDTO.getDni());
        if (alumnoO.isPresent()) {
            Automovil alumno = alumnoO.get();
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
*/







