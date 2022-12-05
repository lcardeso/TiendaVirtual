package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_AUTOMOVIL;
import static com.example.demo.Constantes.Constante.MAPPER_TIPO_PAGO_VENTA;

@Service
@Transactional
public class VentaService {

 /*   @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private AutomovilRepository automovilRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ListaNegraPersonaService listaNegraPersonaService;
    @Autowired
    private PagoFinanciadoRepository pagoFinanciadoRepository;

    //Listar Todas Las Ventas
    public List<ObtenerVentaDTO> obtener() {
        List<Venta> lista = ventaRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(lista, ObtenerVentaDTO.class, MAPPER_TIPO_PAGO_VENTA);
    }

    //Listar las ventas por estado (R-C)
    public List<ObtenerVentaDTO> obtenerVentaPorEstado(String codigo) {
        List<Venta> ventasRealizadas = ventaRepository.findByEstadoVenta(codigo);
        return mapperUtils.mapeoListaObjetoObjeto(ventasRealizadas, ObtenerVentaDTO.class, MAPPER_TIPO_PAGO_VENTA);
    }

    //Validar Venta
    private ResponseDto validarVenta(VentaDTO ventaDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (ventaDTO.getIdAutomovil() == null) {
                return respuesta.status("400").message("Es necesario especificar el automóvil.");
            } else if (ventaDTO.getIdPersona() == null) {
                return respuesta.status("400").message("Es necesario especificar la persona.");
            } else if (ventaDTO.getIdMetodoPago() == null) {
                return respuesta.status("400").message("Es necesario especificar el método de pago.");
            } else if (ventaDTO.getPrecioVenta() == null) {
                return respuesta.status("400").message("El precio de venta no es válido.");
            } else {
                return respuesta.status("200").message("La venta ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    private ResponseDto validarLogicaVenta(VentaDTO ventaDTO) {
        try {
            ResponseDto respuesta = new ResponseDto();
            if (!ventaRepository.findById(ventaDTO.getIdAutomovil()).isPresent()) {
                return respuesta.status("400").message("Es necesario especificar el automóvil.");
            } else if (!ventaRepository.findById(ventaDTO.getIdPersona()).isPresent()) {
                return respuesta.status("400").message("Es necesario especificar la persona.");
            } else if (!ventaRepository.findById(ventaDTO.getIdMetodoPago()).isPresent()) {
                return respuesta.status("400").message("Es necesario especificar el método de pago.");
            } else {
                return respuesta.status("200").message("La venta ha sido validada correctamente");
            }
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Adicionar Venta
    public ResponseDto adicionar(VentaDTO ventaDTO) {
        ResponseDto res;
        Optional<Persona> persona = personaRepository.findById(ventaDTO.getIdPersona());
        try {
            if (listaNegraPersonaService.perMorosa(persona.get().getCedula()).getMessage().equals("La persona es MOROSA")) {
                return new ResponseDto().status("400").message("La persona es MOROSA");
            }
            res = validarVenta(ventaDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            } else {
                res = validarLogicaVenta(ventaDTO);
                if (!res.getStatus().equals("200")) {
                    return res;
                }
            }
            Optional<Automovil> automovil = automovilRepository.findById(ventaDTO.getIdAutomovil());
            if (automovil.isEmpty()) {
                return new ResponseDto().status("400").message("El automóvil no existe.");
            } else if (!automovil.get().getEstado().getCodigo().equals("D")) {
                return new ResponseDto().status("400").message("El automóvil no está disponible.");
            }
            if (persona.isEmpty()) {
                return new ResponseDto().status("400").message("La persona no existe.");
            }
            Optional<MetodoDePago> metodoPago = metodoDePagoRepository.findById(ventaDTO.getIdMetodoPago());
            if (metodoPago.isEmpty()) {
                return new ResponseDto().status("400").message("El método de pago no existe.");
            }

            Venta venta = mapperUtils.mapeoObjetoObjeto(ventaDTO, Venta.class);
            venta.automovil(automovil.get()).estadoVenta("R").
                    persona(persona.get()).
                    metodoPago(metodoPago.get()).
                    fechaVenta(LocalDateTime.now());
            if (ventaDTO.getFinanciar()) {
                PagoFinanciado pagoFinanciado = new PagoFinanciado();
                pagoFinanciado.cuotaInicial(ventaDTO.getCuotaInicial()).
                        plazoFinanciacion(ventaDTO.getPlazoFinanciacion()).
                        tipoFinanciacion("Fijo");
                // PagoFinanciado salvePago = pagoFinanciadoRepository.save(pagoFinanciado);
                venta.pagoFinanciado(pagoFinanciado);
            }
            Optional<Estado> estadoVendido = estadoRepository.findByCodigo("V");
            automovil.get().estado(estadoVendido.get());
            automovilRepository.save(automovil.get());
            ventaRepository.save(venta);
            return new ResponseDto().status("200").message("La venta fue realizada exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Ventas por mes, el mes actual
    public List<ObtenerVentaDTO> ventasPorMes() {
        Integer mes = LocalDate.now().getMonthValue();
        List<Venta> ventasPorMes = ventaRepository.findByMes(mes);
        return mapperUtils.mapeoListaObjetoObjeto(ventasPorMes, ObtenerVentaDTO.class, MAPPER_TIPO_PAGO_VENTA);
    }

    //Cancelar venta
    public ResponseDto cancelarVenta(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isPresent() && !venta.get().getEstadoVenta().equals("C")) {
            venta.get().estadoVenta("C");
            Optional<Automovil> automovil = automovilRepository.findById(venta.get().getAutomovil().getId());
            if (automovil.isPresent()) {
                Automovil autoOpt = automovil.get();
                Optional<Estado> estadoA = estadoRepository.findByCodigo("D");
                autoOpt.estado(estadoA.get());
                automovilRepository.save(autoOpt);
                return new ResponseDto().status("200").message("La venta ha sido cancelada");
            }
        }
        return new ResponseDto().status("200").message("La venta ya esta cancelada");
    }

    //Buscar Ventas Realizadas por Mes y Ano
    public AutoVendPorMesDTO buscarAutosVendPorFecha(LocalDate fecha) {
        List<Automovil> autosVendidos = ventaRepository.findByAutosPorMes(fecha);
        Integer cantidad = autosVendidos.size();
        List<AutomovilDTO> listaMapper = mapperUtils.mapeoListaObjetoObjeto(autosVendidos, AutomovilDTO.class, MAPPER_AUTOMOVIL);
        return new AutoVendPorMesDTO().total(cantidad).autos(listaMapper);
    }

    //Calcula la cuota mensual que debe pagar el cliente
    public Double cuotaMensual(CalculoCuotaDTO calculoCuotaDTO) {
        Double interesFijo = 0.2;
        return ((calculoCuotaDTO.getPrecioVenta() - calculoCuotaDTO.getCuotaInicial()) / calculoCuotaDTO.getPlazoFinanciacion()) * interesFijo;

    }

    //Actualizar metodo de pago a una venta
    public ResponseDto actualizarMetodoPago(ActualizarMetPagoVentaDTO actualizarMetPagoVentaDTO) {
        Optional<Venta> ventaOpt = ventaRepository.findById(actualizarMetPagoVentaDTO.getIdVenta());
        if (ventaOpt.isPresent()) {
            if (actualizarMetPagoVentaDTO.getIdMetodoPago() == 1) {
                PagoFinanciado pagoFinVenta = ventaOpt.get().getPagoFinanciado();
                if (pagoFinVenta == null) {
                    PagoFinanciado pagoFinanciado = new PagoFinanciado();
                    pagoFinanciado.cuotaInicial(actualizarMetPagoVentaDTO.getCuotaInicial()).
                            plazoFinanciacion(actualizarMetPagoVentaDTO.getPlazoFinanciacion()).
                            tipoFinanciacion("Fijo");
                    ventaOpt.get().pagoFinanciado(pagoFinanciado);
                } else {
                    pagoFinVenta.plazoFinanciacion(actualizarMetPagoVentaDTO.getPlazoFinanciacion()).
                            cuotaInicial(actualizarMetPagoVentaDTO.getCuotaInicial());
                    ventaOpt.get().pagoFinanciado(pagoFinVenta);
                }
            }
            Optional<MetodoDePago> metPago = metodoDePagoRepository.findById(actualizarMetPagoVentaDTO.getIdMetodoPago());
            if (metPago.isPresent()) {
                ventaOpt.get().metodoPago(metPago.get());
                return new ResponseDto().status("200").message("El método de pago ha sido actualizado");
            }
        }
        return new ResponseDto().status("400").message("Al ha salido mal");
    }*/

}
