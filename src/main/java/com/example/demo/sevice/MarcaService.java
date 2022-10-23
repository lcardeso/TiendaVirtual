package com.example.demo.sevice;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MarcaService {
/*
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
        Motor g = mapperUtils.mapeoObjetoObjeto(grupoDto, Motor.class);
        grupoRepository.save(g);
        return new ResponseDto().status("200").message("El grupo fue creado exitosamente");
    }

    //Obtener Todos
    public List<Motor> obtenerTodos() {
        List<Motor> grupoLista = grupoRepository.findAll();
        return grupoLista;
    }

    //Eliminar grupo a partir del nombre
    public ResponseDto eliminar(Long id) {
        grupoRepository.delete(new Motor().id(id));
        return new ResponseDto().status("200").message("El grupo ha sido eliminado con éxito");
    }

    //Modificar grupo
    public ResponseDto modificar(GrupoDto grupoDto) {
        Optional<Motor> grupoOpt = grupoRepository.findById(grupoDto.getId());
        if (grupoOpt.isPresent()) {
            Motor grupo = grupoOpt.get();
            grupo.nombre(grupo.getNombre()).
                    categoria(grupo.getCategoria());
            grupoRepository.save(grupo);
            return new ResponseDto().status("200").message("El grupo ha sido modificado.");
        }
        return new ResponseDto().status("400").message("Grupo no encontrado");

    }

 */
}
