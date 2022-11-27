package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_AUTOMOVIL;

@Service
@Transactional
public class ListaNegraPersonaService {

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

    //Comprobar si la persona es morosa o no para determinar si se le puede vender un coche o no
    public ResponseDto perMorosa(String cedula) {
        Optional<Persona> persona = personaRepository.findByCedula(cedula);
        if (persona.isPresent()) {
            long cantCancelaciones = ventaRepository.findByCantCancelaciones(cedula);
            if (cantCancelaciones > 2) {
                return new ResponseDto().status("200").message("La persona es MOROSA");
            }
        }
        return new ResponseDto().status("200").message("La persona NO es MOROSA");
    }

    //Listado de las personas morosas
    public PersonaMorosaDTO obtener() {
        List<Persona> perMorosa = ventaRepository.findByCantPersonasMorosas();
        Integer total = perMorosa.size();
        List<PersonaDTO> listaMapper = mapperUtils.mapeoListaObjetoObjeto(perMorosa, PersonaDTO.class);
        return new PersonaMorosaDTO().total(total).personas(listaMapper);
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
