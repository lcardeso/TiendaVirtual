package com.example.demo.sevice;

import com.example.demo.DTO.ActualizarGrupoDto;
import com.example.demo.DTO.DireccionDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.domain.Direccion;
import com.example.demo.domain.Grupo;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_ID_UNIDAD;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    //Listado de todos los usuarios
    public List<Usuario> getAllUsuario() {
        List<Usuario> usuariolist = usuarioRepository.findAll();
        if (usuariolist.isEmpty()) {
            System.out.println("No hay usuarios para mostrar");
        }
        return usuariolist;
    }

    //Eliminar usuario a partir de un id establecido
    public void eliminar(long delusuario) {
        Optional<Usuario> deleteusuario = usuarioRepository.findById(delusuario);
        usuarioRepository.delete(deleteusuario.get());
    }

    //Mostrar usuario a partir de un sexo establecido
    public List<Usuario> usuariosPorSexo(String sexo) throws Exception {
        if (sexo.equals("F") || sexo.equals("M"))
            return usuarioRepository.findBySexo(sexo);
        else
            throw new Exception("El sexo es incorrecto");
    }

    //Obtener usuarios mayor de una edad determinada a partir de la fecha de nacimiento
    public List<Usuario> mayoresDeterminadaEdad(Long edad) throws Exception {
        if (edad <= 0 || edad > 100)
            throw new Exception("El valor de la edad es incorrecto");
        else
            return mayoresEdadX(edad);
    }

    public List<Usuario> mayoresEdadX(Long edad) {
        List<Usuario> usuariolist = usuarioRepository.findAll();
        List<Usuario> mayoresCiertaEdad = new ArrayList<>();
        usuariolist.forEach(usuario -> {
            Long edadusuario = ChronoUnit.YEARS.between(usuario.getFechNac(), LocalDateTime.now());
            if (edadusuario >= edad) {
                mayoresCiertaEdad.add(usuario);
            }
        });
        return mayoresCiertaEdad;
    }

    //Obtener usuarios mayor de una edad determinada a partir de la edad
    public List<Usuario> edadX(Long edad) throws Exception {
        if (edad <= 0 || edad > 100)
            throw new Exception("El valor de la edad es incorrecto");
        if (edad == null) /** 2021-04-23 17:52:05.001  WARN 9032 --- [nio-8106-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: "null"] **/
            throw new NumberFormatException("El valor de la edad no puede ser null");
        else
            return usuarioRepository.findByEdad(edad);
    }

    // Buscar usuario por nombre

    /**
     * Cuando paso un parametro correcto, me retorna bien. el problema esta cuando introduzco un parametro que tiene varios resultados ***javax.persistence.NonUniqueResultException: query did not return a unique result: 2
     **/
    public List<Usuario> BuscarUsuario(String nombre) {
        String nombreNormalizado = Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Usuario> usu = usuarioRepository.findByNombre(nombreNormalizado);
        if (usu.isEmpty())
            System.out.println("No existe ningun usuario");
        return usu;
    }

    //Adicionar un usuario

    public ResponseDto validarUsuario(UsuarioDTO user) {
        ResponseDto response = new ResponseDto();
        if (user.getNombre().isEmpty()) {
            return response.status("400").message("El parámetro nombre no puede ser null.");
        } else if (user.getPrimerApellido().isEmpty()) {
            return response.status("400").message("El parámetro primer apellido no puede ser null.");
        } else if (user.getSexo().isEmpty()) {
            return response.status("400").message("El parámetro sexo no puede ser null.");
        } else if (user.getEdad() == null) {
            return response.status("400").message("El parámetro edad no puede ser null.");
        } else if (user.getFechNac() == null) {
            return response.status("400").message("El parámetro fecha de nacimiento no puede ser null.");
        } else if (usuarioRepository.findByNombreAndPrimApellido(user.getNombre(), user.getPrimerApellido()).isPresent()) {
            return response.status("400").message("La usuario ya existe");
        } else {
            return adicionar(user);
        }
    }

    public ResponseDto adicionar(UsuarioDTO usu) {
        Usuario usuarioEntity = mapperUtils.mapeoObjetoObjeto(usu, Usuario.class, MAPPER_ID_UNIDAD);
        if (usu.getDireccionId() != null) {
            Direccion d = new Direccion().id(usu.getDireccionId());
            usuarioEntity.direccion(d);
        }
            usuarioRepository.save(usuarioEntity);
        return new ResponseDto().status("200").message("El usuario fue creada exitosamente");
    }

    //Adicionar un usuario
    public ResponseDto actualizarGrupo(ActualizarGrupoDto usu) {
        Optional<Usuario> usuarioEntity = usuarioRepository.findById(usu.getIdUsuario());
        if (usuarioEntity.isPresent()) {
            Grupo grupoEntity = new Grupo().id(usu.getIdGrupo());
            usuarioEntity.get().grupo(grupoEntity);
            usuarioRepository.save(usuarioEntity.get());
            return new ResponseDto().status("200").message("El grupo fue actualizado al usuario : " + usuarioEntity.get().getNombre());
        }
        return new ResponseDto().status("200").message("El usuario con id : " + usu.getIdUsuario() + "no existe.");
    }

    /**No encuetro solucion */
      /*  @PathParam("{usuarioId}")
    private Response findUsuario(@PathParam(usuarioId) Long usuarioId) {
        Usuario usuario = UsuarioDTO.findUsuarioById(usuarioId);
        Direccion direccion = UsuarioDTO.findDireccionByUsuario(usuarioId);
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombreCompleto(usuario.getNombre() + "" + usuario.getPrimApellido() + "" + usuario.getSegApellido());
        dto.setSexo(usuario.getSexo());
        dto.setFechNac(usuario.getFechNac());
        dto.setEdad(usuario.getEdad());
        dto.setId(direccion.id());
        dto.setId(usuario.getId());

        return Response.ok(dto).build();
    }*/


    //Update usuario
   /* public void update(Usuario updusuario) {
        List<Usuario> usuariolist = usuarioRepository.findAllById(updusuario.getId());
        usuariolist.;
        usuarioRepository.save(updusuario);
    }*/


    //Eliminar usuario
 /*   public void eliminar(Usuario delusuario) {
        Usuario deleteusu = usuarioRepository.getOne(delusuario.getId());
        usuarioRepository.delete(deleteusu);
    }

  */

    /** Codigos de ejemplo */
/*    ****** Mostrar mujeres hay ********
    public List<Usuario> mujeres() {
        List<Usuario> mejeres = new ArrayList<>();
        List<Usuario> usuarioslist = usuarioRepository.findAll();
        usuarioslist.stream().filter(usuario -> usuario.getSexo().equalsIgnoreCase("F")).forEach(mujer -> {
            mejeres.add(mujer);
        });
        return mejeres;
    }
*/

}






