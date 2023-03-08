package com.example.demo.service;

import com.example.demo.DTO.DireccionDTO;
import com.example.demo.DTO.LaboratorioDTO;
import com.example.demo.DTO.ListarLaboratorioDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Laboratorio;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.repository.LaboratorioRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_LABORATORIO;

@Service
@Transactional
public class LaboratorioService {

    @Autowired
    LaboratorioRepository laboratorioRepository;
    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    DireccionService direccionService;
    @Autowired
    DireccionRepository direccionRepository;

    public List<ListarLaboratorioDTO> listar() {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(laboratorios, ListarLaboratorioDTO.class, MAPPER_LABORATORIO);
    }

    public ResponseDto validar(LaboratorioDTO laboratorioDTO) {
        ResponseDto resp = new ResponseDto();
        if (laboratorioDTO.getNombre().isEmpty()) {
            return resp.status("400").message("El nombre no es válido.");
        }
        if (laboratorioDTO.getTelefono() == null) {
            return resp.status("400").message("El teléfono no es válido.");
        }
        resp = direccionService.validarDireccion(laboratorioDTO.getDireccionDTO());
        if (!resp.getStatus().equals("200")) {
            return resp;
        }
        return resp.status("200").message("El laboratorio ha sido validado correctamente.");
    }

    public ResponseDto adicionar(LaboratorioDTO laboratorioDTO) {
        ResponseDto resp;
        resp = validar(laboratorioDTO);
        if (!resp.getStatus().equals("200")) {
            return resp;
        }
        if (laboratorioRepository.findByNombre(laboratorioDTO.getNombre()).isPresent()) {
            return resp.status("400").message("El laboratorio ya existe.");
        }
        Laboratorio laboratorio = mapperUtils.mapeoObjetoObjeto(laboratorioDTO, Laboratorio.class);
        DireccionDTO direccionDTO = laboratorioDTO.getDireccionDTO();
        Optional<Direccion> direccion = direccionRepository.findByRefCastral(direccionDTO.getRefCastral());
        direccion.ifPresent(laboratorio::direccion);
        if (direccion.isEmpty()) {
            Direccion direccionNueva = mapperUtils.mapeoObjetoObjeto(direccionDTO, Direccion.class);
            direccionRepository.save(direccionNueva);
            laboratorio.direccion(direccionNueva);
        }
        laboratorioRepository.save(laboratorio);
        return resp.status("200").message("El laboratorio fue creado correctamente.");
    }

    public ResponseDto modTelefono(Long id, Integer telefono) {
        ResponseDto resp = new ResponseDto();
        Optional<Laboratorio> laboratorioOpt = laboratorioRepository.findById(id);
        if (laboratorioOpt.isEmpty()) {
            return resp.status("400").message("El laboratorio no existe.");
        }

        if (String.valueOf(telefono).length() != 9) {
            return resp.status("400").message("El teléfono no es válido.");
        }
        Laboratorio laboratorio = laboratorioOpt.get();
        laboratorio.telefono(telefono);
        return resp.status("200").message("El teléfono del laboratorio ha sido modificado correctamente.");
    }

    public ResponseDto eliminar(Long idLaboratorio) {
        laboratorioRepository.findById(idLaboratorio);
        return new ResponseDto().status("200").message("El laboratorio ha sido eliminado correctamente");
    }
}

