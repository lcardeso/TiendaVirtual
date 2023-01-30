package com.example.demo.service;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.SolicitudCompraDTO;
import com.example.demo.domain.Farmacia;
import com.example.demo.domain.Laboratorio;
import com.example.demo.domain.Medicamento;
import com.example.demo.domain.SolicitudCompra;
import com.example.demo.repository.FarmaciaRepository;
import com.example.demo.repository.LaboratorioRepository;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.repository.SolicitudCompraRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_SOLICITUD_COMPRA;

@Service
@Transactional
public class SolicitudCompraService {

    @Autowired
    MapperUtils mapperUtils;
    @Autowired
    SolicitudCompraRepository solicitudCompraRepository;
    @Autowired
    FarmaciaRepository farmaciaRepository;
    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    LaboratorioRepository laboratorioRepository;


    //Listar solicitudes de compra
    public List<SolicitudCompraDTO> listar() {
        List<SolicitudCompra> solicitud = solicitudCompraRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(solicitud, SolicitudCompraDTO.class, MAPPER_SOLICITUD_COMPRA);
    }

    //Crear solicitud de compra
    public ResponseDto crearSolicitud(SolicitudCompraDTO solicitudCompraDTO) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Farmacia> farmaciaOpt = farmaciaRepository.findById(solicitudCompraDTO.getIdFarmacia());
            if (farmaciaOpt.isEmpty()) {
                return resp.status("400").message("La farmacia no existe");
            }
            Optional<Medicamento> medicamOpt = medicamentoRepository.findById(solicitudCompraDTO.getIdMedicamento());
            if (medicamOpt.isEmpty()) {
                return resp.status("400").message("El medicamento no existe");
            }
            Optional<Laboratorio> labOpt = laboratorioRepository.findById(solicitudCompraDTO.getIdLaboratorio());
            if (labOpt.isEmpty()) {
                return resp.status("400").message("El laboratorio no existe");
            }
            Integer cantidad = solicitudCompraDTO.getCantidad();
            if (cantidad < 0 && cantidad > 100) {
                return resp.status("400").message("Rectifique la cantidad de medicamento que solicita");
            }
            SolicitudCompra solicitud = new SolicitudCompra();
            solicitud.medicamento(medicamOpt.get()).
                    laboratorio(labOpt.get()).
                    farmacia(farmaciaOpt.get()).
                    cantidad(solicitudCompraDTO.getCantidad()).
                    fecha(LocalDateTime.now()).
                    estado("Pendiente");
            SolicitudCompra solicitudC = solicitudCompraRepository.save(solicitud);
            if (solicitudC != null) {
                return resp.status("200").message("Solicitud creada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("400").message("Algo salio mal" + e.getMessage());
        }
        return resp;
    }


    //Cambiar estado a la solicitud
    public ResponseDto cambiarEstado(Long idSolicitud, String estadoNew) {
        ResponseDto resp = new ResponseDto();
        Optional<SolicitudCompra> solOpt = solicitudCompraRepository.findById(idSolicitud);
        if (solOpt.isEmpty()) {
            return resp.status("400").message("La solicitud no existe");
        } else if (!solOpt.get().getEstado().equals("Pendiente")) {
            return resp.status("400").message("Ya le han dado respuesta a la solicitud");
        }
        solicitudCompraRepository.save(solOpt.get().estado(estadoNew));
        return resp.status("200").message("La solicitud de compra ha sido " + estadoNew);
    }

    //Buscar solicitudes por estado
    public List<SolicitudCompraDTO> solicitudesPorEstado(String estado) {
        List<SolicitudCompra> listaSolicitudes = solicitudCompraRepository.findByEstado(estado);
        return mapperUtils.mapeoListaObjetoObjeto(listaSolicitudes, SolicitudCompraDTO.class, MAPPER_SOLICITUD_COMPRA);
    }

}










