package com.example.demo.sevice;

import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Grupo;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GrupoService {

    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private GrupoRepository grupoRepository;


    //Adicionar grupo
    public ResponseDto adicionar(GrupoDto grupoDto) {
        Grupo g = mapperUtils.mapeoObjetoObjeto(grupoDto, Grupo.class);
        grupoRepository.save(g);
        return new ResponseDto().status("200").message("El grupo fue creado exitosamente");
    }
}
