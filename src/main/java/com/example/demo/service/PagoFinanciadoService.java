package com.example.demo.service;

import com.example.demo.DTO.VentaDTO;
import com.example.demo.repository.MetodoDePagoRepository;
import com.example.demo.repository.PagoFInanciadoRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PagoFinanciadoService {
    @Autowired
    public PagoFInanciadoRepository pagoFInanciadoRepository;
    @Autowired
    public VentaRepository ventaRepository;
    @Autowired
    public MetodoDePagoRepository metodoDePagoRepository;


    /**ERROR**/
    public Long cuotaMensual(VentaDTO ventaDTO) {
        Double interesFijo = 0.2;
     /*   metodoDePagoRepository.findByTipoPago("Financiado").ifPresent(metodoDePago -> {
          ((ventaDTO.getPrecioVenta() - ventaDTO.getCuotaInicial()) / ventaDTO.getPlazoFinanciacion()) * interesFijo;
        };

*/
        // pagoFInanciadoRepository.findByTipoFinanciacion("Financiado");

        if (ventaDTO.getIdMetodoPago().equals()) {
        }
        return null;


    }







/*
    @Autowired
    public MapperUtils mapperUtils;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AutomovilRepository alumnoRepository;


     //validar un usuario
    public ResponseDto validarPersona(PersonaDTO personaDTO) {
        ResponseDto respuesta = new ResponseDto();
        if (personaDTO.getNombre().isEmpty()) {
            return respuesta.status("400").message("El nombre no es válido.");
        } else if (personaDTO.getPrimApellido().isEmpty()) {
            return respuesta.status("400").message("El primer apellido no es válido.");
        } else if (personaDTO.getSexo().isEmpty()) {
            return respuesta.status("400").message("El sexo no es válido.");
        } else if (personaDTO.getEdad() == null) {
            return respuesta.status("400").message("La edad no es válida.");
        } else if (personaDTO.getFechNac() == null) {
            return respuesta.status("400").message("La fecha de nacimiento no es válida.");
        } else if (personaDTO.getGrupoId() == null) {
            return respuesta.status("400").message("La persona debe pertenecer a un grupo.");
        } else if (personaRepository.findByNombreAndPrimApellido(personaDTO.getNombre(), personaDTO.getPrimApellido()).isPresent()) {
            return respuesta.status("400").message("La persona ya existe");
        } else {
            return respuesta.status("200").message("La persona ha sido validada correctamente");
        }
    }

    //Listado de todas las personas
    public List<Color> obtenerTodos() {
        List<Color> usuarioList = personaRepository.findAll();
        usuarioList.forEach(usuario -> {
            if (usuario instanceof Automovil) {
                System.out.println("Alumno:" + usuario.getNombre());
            } else {
                System.out.println("Profesor:" + usuario.getNombre());
            }
        });
        if (usuarioList.isEmpty()) {
            System.out.println("No hay usuarios para mostrar");
        }
        return usuarioList;
    }

    /*
    //adicionar un usuario
    public ResponseDto adicionar(PersonaDTO usu) {
        Persona usuarioEntity = mapperUtils.mapeoObjetoObjeto(usu, Persona.class, MAPPER_ID_UNIDAD);
        if (usu.getDireccionId() != null) {
            Direccion d = new Direccion().id(usu.getDireccionId());
            usuarioEntity.direccion(d);
        }
        if (usu.getGrupoId() != null) {
            Grupo g = new Grupo().id(usu.getGrupoId());
            usuarioEntity.grupo(g);
        }
        personaRepository.save(usuarioEntity);
        return new ResponseDto().status("200").message("El usuario fue creado exitosamente");
    }


    public ResponseDto actualizarGrupo(ActualizarGrupoDto usu) {
        ResponseDto resp = new ResponseDto();
        personaRepository.findById(usu.getIdUsuario()).ifPresent(u -> {
            if (u.getGrupo().getId().equals(usu.getIdGrupo())) {
                resp.status("400").message("El usuario ya pertenece al grupo " + u.getGrupo().getNombre());
            }
            Grupo grupoEntity = new Grupo().id(usu.getIdGrupo());
            u.grupo(grupoEntity);
            personaRepository.save(u);
            resp.status("200").message("El grupo fue actualizado al usuario : " + u.getNombre());
        });
        resp.status("404").message("El usuario con id : " + usu.getIdUsuario() + " no existe.");
        return resp;
    }

    //Actualizar la direccion a un usuario
    public ResponseDto actualizarDireccion(ActualizarDireccionDto dirUsuario) {
        ResponseDto respuesta = new ResponseDto();
        personaRepository.findById(dirUsuario.getIdUsuario()).ifPresent(usuario -> {
            Direccion direccion = usuario.getDireccion();
            Optional<Direccion> dirNueva = direccionRepository.findById(dirUsuario.getIdDireccion());
            if (direccion != null && direccion.getId().equals(dirUsuario.getIdDireccion())) {
                respuesta.status("400").message("El usuario ya vive en calle:" + direccion.getCalle() + ", númeroApto:" + direccion.getNumeroApto() + ", códigoPostal:" + direccion.getCodigoPostal());
            } else if (dirNueva.isEmpty()) {
                respuesta.status("404").message("La dirección con id : " + dirUsuario.getIdDireccion() + " no existe.");
            } else {
                usuario.direccion(dirNueva.get());
                personaRepository.save(usuario);
                respuesta.status("200").message("La dirección fue actualizada al usuario : " + usuario.getNombre());
            }
        });
        return respuesta;
    }

        // Buscar usuario por nombre
    public List<Persona> buscarUsuario(String nombre) {

        List<Persona> usu = personaRepository.findByNombre(nombreNormalizado);
        if (usu.isEmpty()) System.out.println("No existe ningun usuario");
        return usu;
    }

      //Mostrar usuario a partir de un sexo establecido
    public List<Persona> usuariosPorSexo(String sexo) throws Exception {
        return personaRepository.findBySexo(sexo);
    }

       //Eliminar usuario a partir de un id establecido
    public ResponseDto eliminar(Long delusuario) {
        Optional<Persona> deleteusuario = personaRepository.findById(delusuario);
        if (deleteusuario.isPresent()) {
            personaRepository.delete(deleteusuario.get());
            return new ResponseDto().status("200").message("El usuario ha sido eliminado con exito");
        } else {
            return new ResponseDto().status("400").message("El usuario no existe");
        }
    }

        //Obtener usuarios de una edad determinada
    public List<Persona> edadX(Integer edad) throws Exception {
        return personaRepository.findByEdad(edad);
    }
   */


}






