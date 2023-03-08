package com.example.demo.service;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.TituloDTO;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Titulo;
import com.example.demo.repository.DireccionRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TituloService {


    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private DireccionRepository direccionRepository;


    //Validar Titulo
    public ResponseDto validarTitulo(TituloDTO titulo) {

        ResponseDto respuesta = new ResponseDto();
        if (titulo.getNombre().isEmpty()) {
            return respuesta.status("400").message("El nombre no es válido.");
        } else if (titulo.getFecha() == null) {
            return respuesta.status("400").message("la fecha no es válida.");
        } else if (titulo.getInstitucion().isEmpty()) {
            return respuesta.status("400").message("La institución no es válida.");
        } else if (titulo.getNumRegistro() == null) {
            return respuesta.status("400").message("El número de registro no es válido.");
        } else if (titulo.getEspecialidad().isEmpty()) {
            return respuesta.status("400").message("La especialidad no es válida.");
        } else {
            return respuesta.status("200").message("el título ha sido validado correctamente");
        }
    }


}






