package com.example.demo.sevice;

import com.example.demo.DTO.DireccionDto;
import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public ResponseDto validarDireccion(DireccionDto dir) {
        ResponseDto response = new ResponseDto();
        if (dir.getCalle().isEmpty()) {
            return response.status("400").message("El parámetro calle no puede ser null.");
        } else if (dir.getNumeroApto().isEmpty()) {
            return response.status("400").message("El parámetro número Apto no puede ser null.");
        } else if (dir.getCodigoPostal().isEmpty()) {
            return response.status("400").message("El parámetro código postal no puede ser null.");
        } else if (direccionRepository.findByCalleAndNumeroApto(dir.getCalle(), dir.getNumeroApto()).isPresent()) {
            return response.status("400").message("La dirección ya existe");
        } else {
            return adicionar(dir);
        }
    }

    //Adicionar dirección
    private ResponseDto adicionar(DireccionDto dir) {
        Direccion d = mapperUtils.mapeoObjetoObjeto(dir, Direccion.class);
        direccionRepository.save(d);
        return new ResponseDto().status("200").message("La dirección fue creada exitosamente");
    }

    //Obtener Todos
    public List<Direccion> getAllDireccion() {
        List<Direccion> direccionlist = direccionRepository.findAll();
        if (direccionlist.isEmpty()) {
            System.out.println("No hay direcciones para mostrar");
        }
        return direccionlist;
    }

    //Eliminar a partir del id
    public ResponseDto eliminar(Long identdir) {
        Optional<Direccion> deletedir = direccionRepository.findById(identdir);
        if (deletedir.isPresent()) {
            direccionRepository.delete(deletedir.get());
            return new ResponseDto().status("200").message("La direccion ha sido eliminado con exito");
        } else {
            return new ResponseDto().status("400").message("la direccion no existe");
        }
    }

    public List<Direccion> buscarDireccion(String codigoPostal) {
        String nombreNormalizado = Normalizer.normalize(codigoPostal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Direccion> direccion = direccionRepository.findByCodigoPostal(nombreNormalizado);
        if (direccion.isEmpty())
            System.out.println("No existe ningun usuario");
        return direccion;
    }
}
