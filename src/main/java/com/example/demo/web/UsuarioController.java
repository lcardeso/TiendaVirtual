package com.example.demo.web;

import com.example.demo.domain.Usuario;
import com.example.demo.sevice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ose")

public class UsuarioController {
    //  private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/obtenerTodos")
    public List<Usuario> obtenerTodos() {
        //  log.debug("RESTobtenertodos request to get all Usuarios");
        return usuarioService.getAllUsuario();
    }

    @DeleteMapping("/eliminarUsuario")
    public void deleteUsuario(long delusuario) {
        usuarioService.eliminar(delusuario);
    }


    @PostMapping(value = "/mostarUsuarioPorSexo", params = "sexo")
    public ResponseEntity<List<Usuario>> motrarUsuarioPorSexo(@RequestParam("sexo") String sexo) throws Exception {
        return ResponseEntity.ok().body(usuarioService.usuariosPorSexo(sexo));
    }


    /**
     * Muestra Usuarios Mayores de una edad determinada a traves del metodo POST
     */
    /**
     * Error al enviar los datos por POST ****2021-04-23 04:44:34.572  WARN 6772 --- [nio-8106-exec-5] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.UnsatisfiedServletRequestParameterException: Parameter conditions "edad" not met for actual request parameters: ]
     **/
    @PostMapping(value = "/mayoresDeterminadaEdad", params = "edad")
    public ResponseEntity<List<Usuario>> mayoresDeterminadaEdad(@Valid @RequestBody @RequestParam("edad") Long edad) throws Exception {
        return ResponseEntity.ok().body(usuarioService.mayoresDeterminadaEdad(edad));
    }

    /**
     * Muestra Usuarios de una eterminada edad a traves del metodo POST
     */
    @PostMapping(value = "/edadX", params = "edad")
    public ResponseEntity<List<Usuario>> edadX(@RequestParam("edad") Long edad) throws Exception {
        return ResponseEntity.ok().body(usuarioService.edadX(edad));
    }

    /**
     * Busca un Usuario por nombre
     */

    @PostMapping(value = "/usuarioXNombre", params = "nombre")
    public ResponseEntity<List<Usuario>> usuarioXNombre(@RequestParam("nombre") String nombre) {
        return ResponseEntity.ok().body(usuarioService.BuscarUsuario(nombre));
    }


    @PostMapping("/adicionarUsuarios/")
    public ResponseEntity<Usuario> addUsuarioList(@Valid @RequestBody Usuario addusuario) throws URISyntaxException {
        Usuario addusu = usuarioService.adicionar(addusuario);
        return ResponseEntity.created(new URI("/usuario/adicionarUsuarios/" + addusu.getId())).body(addusu);
    }

}


