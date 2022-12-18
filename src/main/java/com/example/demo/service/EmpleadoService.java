package com.example.demo.service;

import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Titulo;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.TituloRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {
/*

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private TituloRepository tituloRepository;
    @Autowired
    private TituloService tituloService;

    //Listar Empleados
    public List<EmpleadoDTO> obtener() {
        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(listaEmpleados, EmpleadoDTO.class);
    }

    //Buscar Empleados por Cedula
    public EmpleadoDTO buscarPorCedula(String cedula) {
        try {
            Optional<Empleado> empleadoOpt = empleadoRepository.findByCedula(cedula);
            if (empleadoOpt.isPresent()) {
                return mapperUtils.mapeoObjetoObjeto(empleadoOpt.get(), EmpleadoDTO.class);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //Buscar Empleados por Cedula LIKE
    public List<String> buscarPorCedulaLike(String cedula) {
        try {
            return empleadoRepository.findByCedulaLike(cedula);
        } catch (Exception e) {
            return null;
        }
    }

    //Buscar Empleados por Cargo
    public List<EmpleadoDTO> buscarPorCargo(String cargo) {
        try {
            List<Empleado> empleadoOpt = empleadoRepository.findByCargo(cargo);
            return mapperUtils.mapeoListaObjetoObjeto(empleadoOpt, EmpleadoDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    //Validar Empleados
    public ResponseDto validarEmpleado(EmpleadoDTO empleadoDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (empleadoDTO.getCedula().isEmpty()) {
                return respuesta.status("400").message("La cédula no es válida.");
            } else if (empleadoDTO.getNombre().isEmpty()) {
                return respuesta.status("400").message("El nombre no es válido.");
            } else if (empleadoDTO.getPrimApellido().isEmpty()) {
                return respuesta.status("400").message("El primer apellido no es válido.");
            } else if (empleadoDTO.getSegApellido().isEmpty()) {
                return respuesta.status("400").message("El segundo apellido no es válido.");
            } else if (empleadoDTO.getSexo().isEmpty()) {
                return respuesta.status("400").message("El sexo no es válido.");
            } else if (empleadoDTO.getFechaNacimiento() == null) {
                return respuesta.status("400").message("La fecha de nacimiento no es válida.");
            } else if (empleadoDTO.getTelefono() == null) {
                return respuesta.status("400").message("La teléfono no es válida.");
            } else if (empleadoDTO.getFechaIngreso() == null) {
                return respuesta.status("400").message("La fecha de ingreso no es válida.");
            } else if (empleadoDTO.getSalario() == null) {
                return respuesta.status("400").message("El salario no es válida.");
            } else if (empleadoDTO.getCargoEmpleado().isEmpty()) {
                return respuesta.status("400").message("El cargo no es válida.");
            } else if (empleadoDTO.getDireccion().getId() == null) {
                return respuesta.status("400").message("La direccion no es válido.");
            } else if (empleadoDTO.getTitulo().getId() == null) {
                return respuesta.status("400").message("La titulo no es válido.");
            } else {
                return respuesta.status("200").message("El empleado ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Validar Logica Empleado
    public ResponseDto validarLogicaEmpleado(EmpleadoDTO empleadoDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (empleadoDTO.getCedula().length() != 9) {
                return respuesta.status("400").message("La cédula no es válida, debe contener 9 caracteres.");
            } else if (String.valueOf(empleadoDTO.getTelefono()).length() != 9) {
                return respuesta.status("400").message("El teléfono no es válido, debe contener 9 caracteres.");
            } else if (empleadoDTO.getFechaNacimiento().isAfter(LocalDateTime.now())) {
                return respuesta.status("400").message("La fecha de nacimiento no es válida.");
            } else if (edad(empleadoDTO.getFechaNacimiento()) < 18) {
                return respuesta.status("400").message("El empleado no tiene la mayoria de edad.");
            } else if (empleadoDTO.getFechaIngreso().isAfter(LocalDateTime.now())) {
                return respuesta.status("400").message("La fecha de ingreso no es válida.");
            } else if (empleadoDTO.getSexo().length() != 1) {
                return respuesta.status("400").message("La sexo debe contener un sólo caracter.");
            } else {
                return respuesta.status("200").message("El empleado ha sido validado correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Adicionar Empleado
    public ResponseDto adicionar(EmpleadoDTO empleadoDTO) {
        ResponseDto res;
        try {
            res = validarEmpleado(empleadoDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            } else {
                res = validarLogicaEmpleado(empleadoDTO);
                if (!res.getStatus().equals("200")) {
                    return res;
                }
            }
            if (empleadoRepository.findByCedula(empleadoDTO.getCedula()).isPresent()) {
                return new ResponseDto().status("400").message("La persona ya existe.");
            }
            Direccion dir = empleadoDTO.getDireccion();
            Direccion direccion = new Direccion();
            if (!direccionService.validarDireccion(dir).getStatus().equals("200")) {
                return direccionService.validarDireccion(dir);
            }
            direccion.calle(dir.getCalle()).
                    numero(dir.getNumero()).
                    municipio(dir.getMunicipio()).
                    provincia(dir.getProvincia());
            if (dir.getPiso() != null) {
                direccion.piso(dir.getPiso());
            }
            direccionRepository.save(direccion);
            Titulo titulo = empleadoDTO.getTitulo();
            Titulo tituloNew = new Titulo();
            if (!tituloService.validarTitulo(titulo).getStatus().equals("200")) {
                return tituloService.validarTitulo(titulo);
            }
            if (tituloRepository.findByNumRegistro(titulo.getNumRegistro()).isPresent()) {
                return new ResponseDto().status("400").message("El titulo ya existe");
            }
            tituloNew.nombre(titulo.getNombre()).fecha(titulo.getFecha()).institucion(titulo.getInstitucion()).numRegistro(titulo.getNumRegistro()).especialidad(titulo.getEspecialidad());
            tituloRepository.save(tituloNew);
            Empleado empleado = mapperUtils.mapeoObjetoObjeto(empleadoDTO, Empleado.class);
            empleadoRepository.save(empleado);
            return new ResponseDto().status("200").message("El empleado fue creado exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Modificar Empleado
    public ResponseDto modificar(EmpleadoDTO empleadoDTO) {
        try {
            Optional<Empleado> emplOpt = empleadoRepository.findByCedula(empleadoDTO.getCedula());
            ResponseDto res;
            res = validarEmpleado(empleadoDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            } else {
                res = validarLogicaEmpleado(empleadoDTO);
                if (res.getStatus().equals("200")) {
                    Empleado empleado = emplOpt.get();
                    Direccion emplDir = empleado.getDireccion();
                    Direccion dir = new Direccion();
                    if (!direccionService.validarDireccion(emplDir).getStatus().equals("200")) {
                        return direccionService.validarDireccion(emplDir);
                    }
                    dir.calle(emplDir.getCalle()).
                            numero(emplDir.getNumero()).
                            municipio(emplDir.getMunicipio()).
                            provincia(emplDir.getProvincia());
                    if (emplDir.getPiso() != null) {
                        dir.piso(emplDir.getPiso());
                    }
                    direccionRepository.save(dir);
                    Titulo titulo = empleadoDTO.getTitulo();
                    Titulo tituloNew = new Titulo();
                    if (!tituloService.validarTitulo(titulo).getStatus().equals("200")) {
                        return tituloService.validarTitulo(titulo);
                    }
                    if (tituloRepository.findByNumRegistro(titulo.getNumRegistro()).isPresent()) {
                        tituloNew.nombre(titulo.getNombre()).
                                fecha(titulo.getFecha()).
                                institucion(titulo.getInstitucion()).
                                especialidad(titulo.getEspecialidad());
                        tituloRepository.save(tituloNew);
                    } else {
                        tituloNew.nombre(titulo.getNombre()).
                                fecha(titulo.getFecha()).
                                institucion(titulo.getInstitucion()).
                                numRegistro(titulo.getNumRegistro()).
                                especialidad(titulo.getEspecialidad());
                        tituloRepository.save(tituloNew);
                    }

                }
                Empleado emplMapp = mapperUtils.mapeoObjetoObjeto(empleadoDTO, Empleado.class);
                empleadoRepository.save(emplMapp);
                return new ResponseDto().status("200").message("El empleado ha sido modificado");
            }
        } catch (
                Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Validar edad a partir de la fecha de nacimiento
    public Integer edad(LocalDateTime fechaNac) {
        Integer edad = LocalDateTime.now().getYear() - fechaNac.getYear();
        return edad;

    }

*/

}






