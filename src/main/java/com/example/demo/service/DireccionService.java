package com.example.demo.service;

import com.example.demo.DTO.DireccionDTO;
import com.example.demo.DTO.EmpleadoDTO;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Direccion;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private DireccionRepository direccionRepository;


    //Validar Direccion
    public ResponseDto validarDireccion(DireccionDTO direccion) {
        ResponseDto respuesta = new ResponseDto();
        if (direccion.getRefCastral() == null) {
            return respuesta.status("400").message("La referencia castral no esta especificada.");
        } else if (String.valueOf(direccion.getRefCastral()).length() != 9) {
            return respuesta.status("400").message("La referencia castral es válida.");
        } else if (direccion.getCalle().isEmpty()) {
            return respuesta.status("400").message("La calle no es válida.");
        } else if (direccion.getNumero() == null) {
            return respuesta.status("400").message("El número de la calle no es válido.");
        } else if (direccion.getCodigoPostal() == null) {
            return respuesta.status("400").message("El código postal no es válido.");
        } else if (direccion.getMunicipio().isEmpty()) {
            return respuesta.status("400").message("El municipio no es válido.");
        } else if (direccion.getProvincia().isEmpty()) {
            return respuesta.status("400").message("La provincia no es válida.");
        } else {
            return respuesta.status("200").message("La direccion ha sido validada correctamente");
        }
    }

    //Adicionar Direccion


}






