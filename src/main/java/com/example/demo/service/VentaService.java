package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.example.demo.Constantes.Constante.MAPPER_TIPO_PAGO_VENTA;

@Service
@Transactional
public class VentaService {

    @Autowired
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
            if (ventaDTO.getIdAutomovil() == null /**|| ventaRepository.findByIdAutomovil(ventaDTO.getIdAutomovil()).isPresent()**/) {
                return respuesta.status("400").message("Es necesario especificar el automóvil.");
            } else if (ventaDTO.getIdPersona() == null /**|| !ventaRepository.findByIdPersona(ventaDTO.getIdPersona()).isPresent()**/) {
                return respuesta.status("400").message("Es necesario especificar la persona.");
            } else if (ventaDTO.getIdMetodoPago() == null /**|| !ventaRepository.findByIdMetodoPago(ventaDTO.getIdMetodoPago()).isPresent()**/) {
                return respuesta.status("400").message("Es necesario especificar el método de pago.");
            } else if (ventaDTO.getPrecioVenta().isEmpty()) {
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

    /**
     * SUGERENCIA
     **/
    //Adicionar Venta
    public ResponseDto adicionar(VentaDTO ventaDTO) {
        ResponseDto res;
        try {
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
            Optional<Persona> persona = personaRepository.findById(ventaDTO.getIdPersona());
            if (persona.isEmpty()) {
                return new ResponseDto().status("400").message("La persona no existe.");
            }
            Optional<MetodoDePago> metodoPago = metodoDePagoRepository.findById(ventaDTO.getIdMetodoPago());
            if (metodoPago.isEmpty()) {
                return new ResponseDto().status("400").message("El método de pago no existe.");
            }
            Venta venta = mapperUtils.mapeoObjetoObjeto(ventaDTO, Venta.class);
            venta.automovil(automovil.get()).
                    estadoVenta("R").
                    persona(persona.get()).
                    metodoPago(metodoPago.get()).
                    fechaVenta(LocalDateTime.now());
            //  automovil.get().estado(new Estado().codigo("V"));
            //estadoRepository.save(new Estado().codigo("V"));
            ventaRepository.save(venta);
            return new ResponseDto().status("200").message("La venta fue realizada exitosamente");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal" + e.getMessage());
        }
    }

    //Automoviles vendidos por mes
    public List<AutomovilDTO> autosVendidosPorMes(String mes) {
        // Optional<Automovil> autos = automovilRepository.findByMatricula();

        return null;
    }

    /**
     * ERROR
     **/
    //Cancelar venta
    public ResponseDto cancelarVenta(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        venta.get().estadoVenta("C");
        return new ResponseDto().status("200").message("La venta ha sido cancelada");
    }
}







/*

    public ResponseDto validarDireccion(DireccionDto direccion) {
        ResponseDto respuesta = new ResponseDto();
        if (direccion.getCalle().isEmpty()) {
            return respuesta.status("400").message("El parámetro calle no es válido.");
        } else if (direccion.getNumeroApto().isEmpty()) {
            return respuesta.status("400").message("El parámetro número de apartamento no es válido.");
        } else if (direccion.getCodigoPostal().isEmpty()) {
            return respuesta.status("400").message("El parámetro código postal no es válido.");
        } else if (direccionRepository.findByCalleAndNumeroApto(direccion.getCalle(), direccion.getNumeroApto()).isPresent()) {
            return respuesta.status("400").message("La dirección ya existe");
        } else {
            return adicionar(direccion);
        }
    }

    //Adicionar dirección
    private ResponseDto adicionar(DireccionDto direccion) {
        Marca dirMapeada = mapperUtils.mapeoObjetoObjeto(direccion, Marca.class);
        direccionRepository.save(dirMapeada);
        return new ResponseDto().status("200").message("La dirección fue creada exitosamente");
    }

    //Obtener Todos
    public List<Marca> obtenerTodos() {
        List<Marca> direccionList = direccionRepository.findAll();
        if (direccionList.isEmpty()) {
            System.out.println("No hay direcciones para mostrar");
        }
        return direccionList;
    }


    // Eliminar a partir del id
    public ResponseDto eliminar(Long idDireccion) {
        ResponseDto respuesta = new ResponseDto();
        direccionRepository.delete(new Marca().id(idDireccion));
        return respuesta.status("200").message("La dirección ha sido eliminada con éxito");
    }


    //Buscar direccion por código postal
    public List<Marca> buscar(String codigoPostal) {
        String nombreNormalizado = Normalizer.normalize(codigoPostal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Marca> direccion = direccionRepository.findByCodigoPostal(nombreNormalizado);
        if (direccion.isEmpty())
            System.out.println("No existe la dirección.");
        return direccion;
    }

    //Modificar direccion
    public ResponseDto modificar(DireccionDto direccionDto) {
        Optional<Marca> direccionOpt = direccionRepository.findById(direccionDto.getId());
        if (direccionOpt.isPresent()) {
            Marca direccion = direccionOpt.get();
            direccion.calle(direccionDto.getCalle()).
                    numeroApto(direccionDto.getNumeroApto()).
                    codigoPostal(direccionDto.getCodigoPostal());
            direccionRepository.save(direccion);
            return new ResponseDto().status("200").message("La dirección ha sido modificada.");
        }
        return new ResponseDto().status("400").message("Dirección no encontrada");
    }

*/
