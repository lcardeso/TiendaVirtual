package com.example.demo.sevice;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.DireccionDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Persona;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PersonaRepository personaRepository;

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
        Direccion dirMapeada = mapperUtils.mapeoObjetoObjeto(direccion, Direccion.class);
        direccionRepository.save(dirMapeada);
        return new ResponseDto().status("200").message("La dirección fue creada exitosamente");
    }

    //Obtener Todos
    public List<Direccion> obtenerTodos() {
        List<Direccion> direccionList = direccionRepository.findAll();
        if (direccionList.isEmpty()) {
            System.out.println("No hay direcciones para mostrar");
        }
        return direccionList;
    }


    // Eliminar a partir del id
    public ResponseDto eliminar(Long idDireccion) {
        ResponseDto respuesta = new ResponseDto();
        direccionRepository.delete(new Direccion().id(idDireccion));
        return respuesta.status("200").message("La dirección ha sido eliminada con éxito");
    }


    //Buscar direccion por código postal
    public List<Direccion> buscar(String codigoPostal) {
        String nombreNormalizado = Normalizer.normalize(codigoPostal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Direccion> direccion = direccionRepository.findByCodigoPostal(nombreNormalizado);
        if (direccion.isEmpty())
            System.out.println("No existe la dirección.");
        return direccion;
    }

    //Modificar direccion
    public ResponseDto modificar(DireccionDto direccionDto) {
        Optional<Direccion> direccionOpt = direccionRepository.findById(direccionDto.getId());
        if (direccionOpt.isPresent()) {
            Direccion direccion = direccionOpt.get();
            direccion.calle(direccionDto.getCalle()).
                    numeroApto(direccionDto.getNumeroApto()).
                    codigoPostal(direccionDto.getCodigoPostal());
            direccionRepository.save(direccion);
            return new ResponseDto().status("200").message("La dirección ha sido modificada.");
        }
        return new ResponseDto().status("400").message("Dirección no encontrada");
    }


}
