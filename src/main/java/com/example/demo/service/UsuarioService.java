package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Constantes.Constante.MAPPER_EMPLEADO;
import static com.example.demo.Constantes.Constante.MAPPER_USUARIO;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    public MapperUtils mapperUtils;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    PersonaRepository personaRepository;
    /*@Autowired
    BCryptPasswordEncoder passwordEncoder;*/


    //Listar
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return mapperUtils.mapeoListaObjetoObjeto(usuarios, UsuarioDTO.class, MAPPER_USUARIO);
    }

    //Listar usuarios activos
    public List<UsuarioDTO> listarUsuariosPorEstado(Boolean estado) {
        List<Usuario> usuarios = usuarioRepository.findByActivo(estado);
        return mapperUtils.mapeoListaObjetoObjeto(usuarios, UsuarioDTO.class, MAPPER_USUARIO);
    }

    //Validar
    public ResponseDto validar(UsuarioDTO usuarioDTO) {
        ResponseDto respuesta = new ResponseDto();
        if (usuarioDTO.getCedula().isEmpty()) {
            return respuesta.status("400").message("La cédula no es válida.");
        }
        if (usuarioDTO.getRolDescripcion().isEmpty()) {
            return respuesta.status("400").message("El rol no es válido.");
        }
        if (usuarioDTO.getUsuario().isEmpty()) {
            return respuesta.status("400").message("El usuario no es válido.");
        }
        return respuesta.status("200").message("El usuario ha sido validado correctamente.");
    }

    //Adicionar
    public ResponseDto adicionar(UsuarioDTO usuarioDTO) {
        ResponseDto res;
        try {
            res = validar(usuarioDTO);
            if (!res.getStatus().equals("200")) {
                return res;
            }
            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(usuarioDTO.getUsuario());
            if (usuarioOpt.isPresent()) {
                return res.status("400").message("El nombre de usuario ya existe.");
            }
            Optional<Persona> personaOpt = personaRepository.findByCedula(usuarioDTO.getCedula());
            if (personaOpt.isEmpty()) {
                return res.status("400").message("La persona no existe.");
            }
            Optional<Rol> rolOpt = rolRepository.findByDescripcion(usuarioDTO.getRolDescripcion());
            if (rolOpt.isEmpty()) {
                return res.status("400").message("El rol no existe.");
            }
            Usuario usuario = mapperUtils.mapeoObjetoObjeto(usuarioDTO, Usuario.class);
            Persona persona = personaOpt.get();
            usuario.persona(persona)
                    .rol(rolOpt.get())
                   // .contrasenna(passwordEncoder.encode(usuarioDTO.getContrasenna()))
                    .contrasenna(usuarioDTO.getContrasenna())
                    .activo(true);
            usuarioRepository.save(usuario);
            return new ResponseDto().status("200").message("El usuario ha sido creado correctamente.");
        } catch (Exception e) {
            return new ResponseDto().status("500").message("Algo salió mal " + e.getMessage());
        }
    }

    //Reiniciar contrasenna
    public ResponseDto reiniciarContrasenna(Long idUsuario) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
            if (usuarioOpt.isEmpty()) {
                return resp.status("400").message("El usuario no existe.");
            }
            usuarioOpt.get().contrasenna("12345678");
            return resp.status("200").message("La contraseña ha sido reiniciada correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    //Modificar Rol
    public ResponseDto modRol(Long idUsuario, Long idRol) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
            if (usuarioOpt.isEmpty()) {
                return resp.status("400").message("El usuario no existe.");
            }
            Optional<Rol> rolOpt = rolRepository.findById(idRol);
            if (rolOpt.isEmpty()) {
                return resp.status("400").message("El rol no existe.");
            }
            usuarioOpt.get().rol(rolOpt.get());
            return resp.status("200").message("El  rol del usuario ha sido modificado correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    public ResponseDto modEstadoUsuario(Long idUsuario, Boolean estado) {
        ResponseDto resp = new ResponseDto();
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
            if (usuarioOpt.isEmpty()) {
                return resp.status("400").message("El usuario no existe.");
            }
            usuarioOpt.get().activo(estado);
            return resp.status("200").message("El estado ha sido modificado correctamente.");
        } catch (Exception e) {
            return resp.status("400").message("Algo salio mal " + e.getMessage());
        }
    }

    public LoginUsuarioDTO login(LoginDTO loginDTO) {
        LoginUsuarioDTO loginUsuarioDTO = new LoginUsuarioDTO();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuarioAndContrasenna(loginDTO.getUsuario(), loginDTO.getContrasenna());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Persona persona = usuario.getPersona();
            String nombreCompleto = persona.getNombre() + " " + persona.getPrimApellido() + " " + persona.getSegApellido();
            List<PermisosAsociados> permisosAsociados = usuario.getRol().getPermisosAsociados();
            loginUsuarioDTO.setCedula(persona.getCedula());
            loginUsuarioDTO.setUsername(usuario.getUsuario());
            loginUsuarioDTO.setRolDescripcion(usuario.getRol().getDescripcion());
            loginUsuarioDTO.setNombreCompleto(nombreCompleto);
            List<PermisosAsociadosDTO> permisosAsociadosDTO = mapperUtils.mapeoListaObjetoObjeto(permisosAsociados, PermisosAsociadosDTO.class);
            loginUsuarioDTO.setPermisosAsociadosDTOS(permisosAsociadosDTO);
            loginUsuarioDTO.setResponseDto(new ResponseDto().status("200").message("Usuario " + nombreCompleto));
        } else {
            loginUsuarioDTO.setResponseDto(new ResponseDto().status("200").message("Usuario o contraseña incorrecto"));
        }
        return loginUsuarioDTO;
    }


}






