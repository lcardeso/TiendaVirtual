package com.example.demo.sevice;

import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.NombreGrupoDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Grupo;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_ID_UNIDAD;

@Service
@Transactional
public class GrupoService {

    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private GrupoRepository grupoRepository;

    //validar un grupo
    public ResponseDto validarGrupo(GrupoDto grupo) {
        ResponseDto response = new ResponseDto();
        if (grupo.getNombre().isEmpty()) {
            return response.status("400").message("El parámetro nombre no puede ser null.");
        } else if (grupo.getCategoria().isEmpty()) {
            return response.status("400").message("El parámetro categoría no puede ser null.");
        } else if (grupoRepository.findByNombreAndCategoria(grupo.getNombre(), grupo.getCategoria()).isPresent()) {
            return response.status("400").message("La usuario ya existe");
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
    public List<NombreGrupoDto> getAllGrupo() {
        List<Grupo> grupolist = grupoRepository.findAll();
        List<NombreGrupoDto> g = mapperUtils.mapeoListaObjetoObjeto(grupolist, NombreGrupoDto.class);
        if (grupolist.isEmpty()) {
            System.out.println("No hay grupos para mostrar");
        }
        return g;
    }

    //Eliminar grupo
    public ResponseDto eliminar(String nombregrup) {
        Optional<Grupo> deletegrupo = grupoRepository.findByNombre(nombregrup);
        if (deletegrupo.isPresent()) {
            grupoRepository.delete(deletegrupo.get());
            return new ResponseDto().status("200").message("El grupo ha sido eliminado con exito");
        } else {
            return new ResponseDto().status("400").message("El grupo no existe");
        }
    }

}

