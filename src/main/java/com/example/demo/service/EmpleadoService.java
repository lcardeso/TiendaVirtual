package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_EMPLEADO;

@Service
@Transactional
public class EmpleadoService {

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
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private FarmaciaRepository farmaciaRepository;


    //Listar empleados
    public List<EmpleadoDTO> listarEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(empleados, EmpleadoDTO.class, MAPPER_EMPLEADO);
    }

    //Listar empleados activos
    public List<EmpleadoDTO> listarEmpleadosPorEstado(String estado) {
        List<Empleado> empleados = empleadoRepository.findByPermanencia(estado);
        return mapperUtils.mapeoListaObjetoObjeto(empleados, EmpleadoDTO.class, MAPPER_EMPLEADO);
    }

    //Validar Empleados
    public ResponseDto validarEmpleado(EmpleadoDTO empleadoDTO) {
        ResponseDto resp;
        ResponseDto respuesta = new ResponseDto();
        if (empleadoDTO.getSalario() == null) {
            return respuesta.status("400").message("El salario no es válido.");
        } else if (empleadoDTO.getCargo().isEmpty()) {
            return respuesta.status("400").message("El cargo no es válido.");
        } else {
            return respuesta.status("200").message("El empleado ha sido validado correctamente");
        }
    }

    /**
     * ERROR
     */
    //Adicionar Empleado
    public ResponseDto adicionar(EmpleadoDTO empleadoDTO) {
        ResponseDto res;
        try {
            res = personaService.validar(empleadoDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            }
            res = validarEmpleado(empleadoDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            }
            if (empleadoRepository.findByCedula(empleadoDTO.getCedula()).isPresent()) {
                return res.status("400").message("El empleado ya existe");
            }
            Optional<Farmacia> farmacia = farmaciaRepository.findById(empleadoDTO.getIdFarmacia());
            if (farmacia.isEmpty()) {
                return res.status("400").message("La farmacia no esta especificada");
            }
            DireccionDTO dirDTO = empleadoDTO.getDireccion();
            ResponseDto respDir = direccionService.validarDireccion(dirDTO);
            if (!respDir.getStatus().equals("200")) {
                return respDir;
            }
            Direccion direccion = mapperUtils.mapeoObjetoObjeto(dirDTO, Direccion.class);
            Direccion dirSalve = direccionRepository.save(direccion);
            List<TituloDTO> titulosDTO = empleadoDTO.getTitulos();
            List<Titulo> titulos = new ArrayList<>();
            for (TituloDTO tituloDTO : titulosDTO) {
                ResponseDto respTit = tituloService.validarTitulo(tituloDTO);
                if (!respTit.getStatus().equals("200")) {
                    return respTit;
                }
                if (tituloRepository.findByNumRegistro(tituloDTO.getNumRegistro()).isPresent()) {
                    return new ResponseDto().status("400").message("El titulo ya existe");
                }
                Titulo titulo = mapperUtils.mapeoObjetoObjeto(tituloDTO, Titulo.class);
                titulos.add(titulo);
            }
            Empleado empleado = mapperUtils.mapeoObjetoObjeto(empleadoDTO, Empleado.class);
            empleado.direccion(dirSalve);
            empleado.titulos(titulos);
            empleado.farmacia(farmacia.get());
            empleado.permanencia("A");
            empleado.fechaIngreso(LocalDateTime.now());
            empleadoRepository.save(empleado);
            return new ResponseDto().status("200").message("El empleado fue creado exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal " + e.getMessage());
        }
    }

    /**
     * DUDA
     */
    //Modificar Direccion
    public ResponseDto modDireccion(ModDireccionDTO modDireccionDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Empleado> emplOpt = empleadoRepository.findByCedula(modDireccionDTO.getCedula());
            Empleado empleado = emplOpt.get();
            if (emplOpt.isEmpty() || empleado.getPermanencia().equals("B")) {
                return resp.status("400").message("El empleado no existe o ha causado baja");
            }
            DireccionDTO direccionDTO = modDireccionDTO.getDireccionDTO();
            ResponseDto respDir = direccionService.validarDireccion(direccionDTO);
            if (!respDir.getStatus().equals("200")) {
                return respDir;
            }
            // Optional<Direccion> direccionOptional = direccionRepository.findById(empleado.getDireccion().getId());
            Direccion direccion = mapperUtils.mapeoObjetoObjeto(direccionDTO, Direccion.class);
            Direccion dirSalve = direccionRepository.save(direccion);
            empleado.direccion(dirSalve);
            return resp.status("200").message("Direccion modificada con exito");
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    /**ERROR*/
    //Modificar titulo
    public ResponseDto modTitulo(ModTituloDTO modTituloDTO) {
        ResponseDto resp = new ResponseDto();
        Titulo tituloSalve;
        try {
            Optional<Empleado> emplOpt = empleadoRepository.findByCedula(modTituloDTO.getCedula());
            if (emplOpt.isEmpty() || emplOpt.get().getPermanencia().equals("B")) {
                return resp.status("400").message("El empleado no existe o ha causado baja");
            }
            TituloDTO tituloDTO = modTituloDTO.getTitulo();
            ResponseDto respTit = tituloService.validarTitulo(tituloDTO);
            if (!respTit.getStatus().equals("200")) {
                return respTit;
            }
            Optional<Titulo> tituloOptional = tituloRepository.findByNumRegistro(tituloDTO.getNumRegistro());
            if (tituloOptional.isEmpty()) {
                return resp.status("400").message("El titulo no existe");
            }
            tituloSalve = mapperUtils.mapeoObjetoObjeto(tituloDTO, Titulo.class);

            return resp.status("200").message("Titulo modificado con exito");
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Modificar Salario
    public ResponseDto modSalario(ModSalarioDTO modSalarioDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Empleado> empleadoOptional = empleadoRepository.findById(modSalarioDTO.getIdEmpleado());
            Empleado empleado = empleadoOptional.get();
            if (empleadoOptional.isEmpty() || empleado.getPermanencia().equals("B")) {
                return resp.status("400").message("El empleado no existe");
            }
            empleado.salario(empleado.getSalario()).resolucion(empleado.getResolucion());
            empleadoRepository.save(empleado);
            return resp.status("200").message("El salario del empleado con cedula " + empleado.getCedula() + " fue modificado con exito");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Adicionar titulo
    public ResponseDto adicTitulo(AdicTituloDTO adicTituloDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Empleado> emplOpt = empleadoRepository.findByCedula(adicTituloDTO.getCedula());
            Empleado empleado = emplOpt.get();
            if (emplOpt.isEmpty() || empleado.getPermanencia().equals("B")) {
                return resp.status("400").message("El empleado no existe");
            }
            List<Titulo> listaTitulos = new ArrayList<>();
            List<TituloDTO> titulosDTO = adicTituloDTO.getTitulos();
            for (TituloDTO titulo1 : titulosDTO) {
                ResponseDto respTit = tituloService.validarTitulo(titulo1);
                if (!respTit.getStatus().equals("200")) {
                    return respTit;
                }
                if (tituloRepository.findByNumRegistro(titulo1.getNumRegistro()).isPresent()) {
                    return resp.status("400").message("El titulo ya existe" + titulo1);
                }
                Titulo titulo = mapperUtils.mapeoObjetoObjeto(titulosDTO, Titulo.class);
                listaTitulos.add(titulo);
            }
            empleado.titulos(listaTitulos);
            empleadoRepository.save(empleado);
            return resp.status("200").message("Titulos adicionado con exito");

        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Buscar Empleados por Cedula
    public EmpleadoDTO buscarPorCedula(String cedula) {
        try {
            Optional<Empleado> empleadoOpt = empleadoRepository.findByCedula(cedula);
            if (empleadoOpt.isPresent() && !empleadoOpt.get().getPermanencia().equals("B")) {
                return mapperUtils.mapeoObjetoObjeto(empleadoOpt.get(), EmpleadoDTO.class, MAPPER_EMPLEADO);
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

    //Buscar por Cargo
    public List<EmpleadoDTO> buscarCargo(String cargo) {
        List<Empleado> empleados = empleadoRepository.findByCargo(cargo);
        List<Empleado> empleadoList = new ArrayList<>();
        empleados.forEach(empleado -> {
            if (empleado.getPermanencia().equals("A")) {
                empleadoList.add(empleado);
            }
        });
        return mapperUtils.mapeoListaObjetoObjeto(empleadoList, EmpleadoDTO.class, MAPPER_EMPLEADO);
    }

    //Buscar Empleados por Nombre
    public EmpleadoDTO buscarPorNombre(String nombre) {
        try {
            Optional<Empleado> empleadoOpt = empleadoRepository.findByNombre(nombre);
            if (empleadoOpt.isPresent() || empleadoOpt.get().getPermanencia().equals("A")) {
                return mapperUtils.mapeoObjetoObjeto(empleadoOpt.get(), EmpleadoDTO.class, MAPPER_EMPLEADO);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //Buscar Empleados por Nombre
    public List<String> buscarPorNombreLike(String nombre) {
        try {
            return empleadoRepository.findByNombreLike(nombre);
        } catch (Exception e) {
            return null;
        }
    }

    //Dar de baja
    public ResponseDto darBaja(Long idEmpleado) {
        ResponseDto resp = new ResponseDto();
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(idEmpleado);
        Empleado empleado = empleadoOptional.get();
        if (empleado.getPermanencia().equals("B") || empleadoOptional.isEmpty()) {
            return resp.status("400").message("El empleado no existe o ya fue dado de baja");
        } else {
            empleado.permanencia("B");
            empleadoRepository.save(empleado);
            return resp.status("200").message("El empleado fue dado de baja exitosamente");
        }
    }
}






