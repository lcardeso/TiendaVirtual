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
import static com.example.demo.Constantes.Constante.MAPPER_PASANTE;

@Service
@Transactional
public class PasantiaService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private PasantiaRepository pasantiaRepository;
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

    //Listar pasantes
    public List<PasanteDTO> listarPasantes() {
        List<Pasante> pasantes = pasantiaRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(pasantes, PasanteDTO.class, MAPPER_PASANTE);
    }

    //Listar pasantes activos
    public List<PasanteDTO> listarPasantesPorEstado(String estado) {
        List<Pasante> pasantes = pasantiaRepository.findByPermanencia(estado);
        return mapperUtils.mapeoListaObjetoObjeto(pasantes, PasanteDTO.class, MAPPER_PASANTE);
    }

    //Validar Pasantes
    public ResponseDto validarPasante(PasanteDTO pasanteDTO) {
        ResponseDto resp;
        ResponseDto respuesta = new ResponseDto();
        if (pasanteDTO.getFechaFin() == null) {
            return respuesta.status("400").message("La fecha de finalización no es válida.");
        } else if (pasanteDTO.getInstitucion().isEmpty()) {
            return respuesta.status("400").message("La institución no es válida.");
        } else {
            return respuesta.status("200").message("El pasante ha sido validado correctamente.");
        }
    }

    //Adicionar Pasante
    public ResponseDto adicionar(PasanteDTO pasanteDTO) {
        ResponseDto res;
        try {
            res = personaService.validar(pasanteDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            }
            res = validarPasante(pasanteDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            }
            if (pasantiaRepository.findByCedula(pasanteDTO.getCedula()).isPresent()) {
                return res.status("400").message("El pasante ya existe.");
            }
            Optional<Farmacia> farmacia = farmaciaRepository.findById(pasanteDTO.getIdFarmacia());
            if (farmacia.isEmpty()) {
                return res.status("400").message("La farmacia no existe..");
            }
            DireccionDTO dirDTO = pasanteDTO.getDireccion();
            ResponseDto respDir = direccionService.validarDireccion(dirDTO);
            if (!respDir.getStatus().equals("200")) {
                return respDir;
            }
            Direccion direccion = mapperUtils.mapeoObjetoObjeto(dirDTO, Direccion.class);
            Direccion dirSalve = direccionRepository.save(direccion);
            List<TituloDTO> titulosDTO = pasanteDTO.getTitulos();
            List<Titulo> titulos = new ArrayList<>();
            for (TituloDTO tituloDTO : titulosDTO) {
                ResponseDto respTit = tituloService.validarTitulo(tituloDTO);
                if (!respTit.getStatus().equals("200")) {
                    return respTit;
                }
                if (tituloRepository.findByNumRegistro(tituloDTO.getNumRegistro()).isPresent()) {
                    return new ResponseDto().status("400").message("El título ya existe.");
                }
                Titulo titulo = mapperUtils.mapeoObjetoObjeto(tituloDTO, Titulo.class);
                titulos.add(titulo);
            }
            Pasante pasante = mapperUtils.mapeoObjetoObjeto(pasanteDTO, Pasante.class);
            pasante.direccion(dirSalve);
            pasante.titulos(titulos);
            pasante.farmacia(farmacia.get());
            pasantiaRepository.save(pasante);
            return new ResponseDto().status("200").message("El pasante ha sido creado correctamente.");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal " + e.getMessage());
        }
    }

    //Modificar Direccion
    public ResponseDto modDireccion(ModDireccionDTO modDireccionDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Pasante> pasanteOpt = pasantiaRepository.findByCedula(modDireccionDTO.getCedula());
            if (pasanteOpt.isEmpty()) {
                return resp.status("400").message("El pasante no existe.");
            }
            DireccionDTO direccionDTO = modDireccionDTO.getDireccionDTO();
            ResponseDto respDir = direccionService.validarDireccion(direccionDTO);
            if (!respDir.getStatus().equals("200")) {
                return respDir;
            }
            Pasante pasante = pasanteOpt.get();
            if (pasante.getPermanencia().equals("B")) {
                return resp.status("400").message("El pasante ha causado baja.");
            }
            Optional<Direccion> direccionOpt = direccionRepository.findByRefCastral(direccionDTO.getRefCastral());
            if (direccionOpt.isPresent()) {
                pasante.direccion(direccionOpt.get());
            } else {
                Direccion direccion = mapperUtils.mapeoObjetoObjeto(direccionDTO, Direccion.class);
                Direccion dirSalve = direccionRepository.save(direccion);
                pasante.direccion(dirSalve);
            }
            return resp.status("200").message("La dirección ha sido modificada correctamente.");
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Modificar titulo
    public ResponseDto modTitulo(ModTituloDTO modTituloDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            TituloDTO tituloDTO = modTituloDTO.getTitulo();
            ResponseDto respTit = tituloService.validarTitulo(tituloDTO);
            if (!respTit.getStatus().equals("200")) {
                return respTit;
            }
            Optional<Titulo> tituloOptional = tituloRepository.findByNumRegistro(tituloDTO.getNumRegistro());
            if (tituloOptional.isEmpty()) {
                return resp.status("400").message("El título  no existe.");
            }
            tituloOptional.get().nombre(tituloDTO.getNombre()).
                    fecha(tituloDTO.getFecha()).
                    especialidad(tituloDTO.getEspecialidad()).
                    institucion(tituloDTO.getInstitucion());
            return resp.status("200").message("El título  ha sido modificado correctamente.");
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Adicionar titulo
    public ResponseDto adicTitulo(AdicTituloDTO adicTituloDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Pasante> pasanteOpt = pasantiaRepository.findByCedula(adicTituloDTO.getCedula());
            if (pasanteOpt.isEmpty()) {
                return resp.status("400").message("El pasante no existe.");
            }
            List<Titulo> listaTitulos = new ArrayList<>();
            List<TituloDTO> titulosDTO = adicTituloDTO.getTitulos();
            for (TituloDTO titulo1 : titulosDTO) {
                ResponseDto respTit = tituloService.validarTitulo(titulo1);
                if (!respTit.getStatus().equals("200")) {
                    return respTit;
                }
                if (tituloRepository.findByNumRegistro(titulo1.getNumRegistro()).isPresent()) {
                    return resp.status("400").message("El título ya existe" + titulo1);
                }
                Titulo titulo = mapperUtils.mapeoObjetoObjeto(titulosDTO, Titulo.class);
                listaTitulos.add(titulo);
            }
            Pasante pasante = pasanteOpt.get();
            pasante.titulos(listaTitulos);
            pasantiaRepository.save(pasante);
            return resp.status("200").message("Los títulos han sido creado correctamente.");

        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Buscar Pasantes por Cedula
    public PasanteDTO buscarPorCedula(String cedula) {
        try {
            Optional<Pasante> pasanteOpt = pasantiaRepository.findByCedula(cedula);
            if (pasanteOpt.isPresent()) {
                return mapperUtils.mapeoObjetoObjeto(pasanteOpt.get(), PasanteDTO.class, MAPPER_PASANTE);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //Buscar Pasante por Cedula LIKE
    public List<String> buscarPorCedulaLike(String cedula) {
        try {
            return pasantiaRepository.findByCedulaLike(cedula);
        } catch (Exception e) {
            return null;
        }
    }

    //Buscar Empleados por Nombre
    public PasanteDTO buscarPorNombre(String nombre) {
        try {
            Optional<Pasante> pasanteOpt = pasantiaRepository.findByNombre(nombre);
            if (pasanteOpt.isPresent()) {
                return mapperUtils.mapeoObjetoObjeto(pasanteOpt.get(), PasanteDTO.class, MAPPER_PASANTE);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //Buscar Empleados por Nombre
    public List<String> buscarPorNombreLike(String nombre) {
        try {
            return pasantiaRepository.findByNombreLike(nombre);
        } catch (Exception e) {
            return null;
        }
    }

    //Modificar Fecha Fin
    public ResponseDto modFechaFin(ModFechaFinPasanteDTO modFechaFinPasanteDTO) {
        ResponseDto resp = new ResponseDto();
        Optional<Pasante> pasanteOpt = pasantiaRepository.findById(modFechaFinPasanteDTO.getId());
        if (pasanteOpt.isEmpty()) {
            return resp.status("400").message("El pasante no existe.");
        }
        Pasante pasante = pasanteOpt.get();
        if (pasante.getPermanencia().equals("B")) {
            return resp.status("400").message("El pasante ha causado baja.");
        }
        if (modFechaFinPasanteDTO.getFechaFin().isBefore(LocalDateTime.now())) {
            return resp.status("400").message("La fecha no es válida.");
        }
        pasante.fechaFin(modFechaFinPasanteDTO.getFechaFin()).motivo(modFechaFinPasanteDTO.getMotivo());
        pasantiaRepository.save(pasante);
        return resp.status("200").message("La fecha fin del pasante ha sido modificada correctamente.");
    }

    //Dar de baja
    public ResponseDto darBaja(Long idPasante) {
        ResponseDto resp = new ResponseDto();
        Optional<Pasante> pasanteOpt = pasantiaRepository.findById(idPasante);
        if (pasanteOpt.isEmpty()) {
            return resp.status("400").message("El pasante no existe.");
        }
        Pasante pasante = pasanteOpt.get();
        if (pasante.getPermanencia().equals("B")) {
            return resp.status("400").message("El pasante ha causado baja.");
        } else {
            pasante.permanencia("B");
            pasantiaRepository.save(pasante);
            return resp.status("200").message("El pasante ha sido dado de baja correctamente.");
        }
    }
}







