package com.example.demo.sevice;

import com.example.demo.DTO.*;
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
        if (deleteusuario.isPresent()){
            usuarioRepository.delete(deleteusuario.get());
        }
        System.out.println("No existe nungun usuario para eliminar");
    }

    //Mostrar usuario a partir de un sexo establecido
    public List<Usuario> usuariosPorSexo(String sexo) throws Exception {
        return usuarioRepository.findBySexo(sexo);
    }

    //Obtener usuarios de una edad determinada
    public List<Usuario> edadX(Integer edad) throws Exception {
        return usuarioRepository.findByEdad(edad);
    }

    // Buscar usuario por nombre
    public List<Usuario> BuscarUsuario(String nombre) {
        String nombreNormalizado = Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        List<Usuario> usu = usuarioRepository.findByNombre(nombreNormalizado);
        if (usu.isEmpty())
            System.out.println("No existe ningun usuario");
        return usu;
    }

    //validar un usuario
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

    //validar un usuario
    public ResponseDto adicionar(UsuarioDTO usu) {
        Usuario usuarioEntity = mapperUtils.mapeoObjetoObjeto(usu, Usuario.class, MAPPER_ID_UNIDAD);
        if (usu.getDireccionId() != null) {
            Direccion d = new Direccion().id(usu.getDireccionId());
            usuarioEntity.direccion(d);
        }
        usuarioRepository.save(usuarioEntity);
        return new ResponseDto().status("200").message("El usuario fue creada exitosamente");
    }

    //Actualizar el grupo a un usuario
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

    //Actualizar la direccion a un usuario
    public ResponseDto actualizarDireccion(ActualizarDireccionDto dirusuario) {
        Optional<Usuario> usuarioEntity = usuarioRepository.findById(dirusuario.getIdUsuario());
        if (usuarioEntity.isPresent()) {
            Direccion direccionEntity = new Direccion().id(dirusuario.getIdDireccion());
            usuarioEntity.get().direccion(direccionEntity);
            usuarioRepository.save(usuarioEntity.get());
            return new ResponseDto().status("200").message("La dirección fue actualizado al usuario : " + usuarioEntity.get().getNombre());
        }
        return new ResponseDto().status("200").message("El usuario con id : " + dirusuario.getIdUsuario() + " no existe.");
    }

    /** //Obtener usuarios mayor de una edad determinada a partir de la fecha de nacimiento
     public List<Usuario> mayoresDeterminadaEdad(Integer edad) throws Exception {




     if (edad <= 0 || edad > 100)
     throw new Exception("El valor de la edad es incorrecto");
     else
     return mayoresEdadX(edad);
     }

     public List<Usuario> mayoresEdadX(Long edad) {
     //  List<Usuario> usuariolist = usuarioRepository.findAll();
     long fechaSegunEdad = LocalDateTime.now().getYear() - edad;



     //   long edadusuario = ChronoUnit.YEARS.between(usuario.getFechNac(), LocalDateTime.now());


     List<Usuario> mayoresCiertaEdad = new ArrayList<>();
     usuariolist.forEach(usuario -> {

     if (edadusuario >= edad) {
     mayoresCiertaEdad.add(usuario);
     }
     });
     return mayoresCiertaEdad;
     }*/

}






