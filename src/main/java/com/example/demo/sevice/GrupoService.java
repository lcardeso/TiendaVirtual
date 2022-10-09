package com.example.demo.sevice;

import com.example.demo.DTO.AlumnoDTO;
import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.NombreGrupoDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.domain.Alumno;
import com.example.demo.domain.Grupo;
import com.example.demo.domain.Persona;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrupoService {

    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private GrupoRepository grupoRepository;

    //validar un grupo
    public ResponseDto validarGrupo(GrupoDto grupo) {
        ResponseDto respuesta = new ResponseDto();
        if (grupo.getNombre().isEmpty()) {
            return respuesta.status("400").message("El parámetro nombre no es válido.");
        } else if (grupo.getCategoria().isEmpty()) {
            return respuesta.status("400").message("El parámetro categoría no es válido.");
        } else if (grupoRepository.findByNombreAndCategoria(grupo.getNombre(), grupo.getCategoria()).isPresent()) {
            return respuesta.status("400").message("La usuario ya existe");
        } else {
            return adicionar(grupo);
        }
    }

    //Adicionar grupo
    public ResponseDto adicionar(GrupoDto grupoDto) {
        Grupo g = mapperUtils.mapeoObjetoObjeto(grupoDto, Grupo.class);
        grupoRepository.save(g);
        return new ResponseDto().status("200").message("El grupo fue creado exitosamente");
    }

    //Obtener Todos
    public List<Grupo> obtenerTodos() {
        List<Grupo> grupoLista = grupoRepository.findAll();
        return grupoLista;
    }

    //Eliminar grupo a partir del nombre
    public ResponseDto eliminar(Long id) {
        grupoRepository.delete(new Grupo().id(id));
        return new ResponseDto().status("200").message("El grupo ha sido eliminado con éxito");
    }

    //Modificar grupo
    public ResponseDto modificar(GrupoDto grupoDto) {
        Optional<Grupo> grupoOpt = grupoRepository.findById(grupoDto.getId());
        if (grupoOpt.isPresent()) {
            Grupo grupo = grupoOpt.get();
            grupo.nombre(grupo.getNombre()).
                    categoria(grupo.getCategoria());
            grupoRepository.save(grupo);
            return new ResponseDto().status("200").message("El grupo ha sido modificado.");
        }
        return new ResponseDto().status("400").message("Grupo no encontrado");

    }
}

