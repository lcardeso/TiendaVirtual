package com.example.demo.service;
import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;

    /**
     * No gestiono el rol
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usOpt = usuarioRepository.findByUsuario(username);
        Usuario us = usOpt.get();
        List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("Admin"));
        Optional<Rol> rolOpt = rolRepository.findByDescripcion(us.getRol().getDescripcion());
        String rol = rolOpt.get().getDescripcion();
        roles.add(new SimpleGrantedAuthority(rol));
       // us.getRol().stream().map(SecurityAuthority::new).toList(); /**Obtener los roles
        UserDetails userDetails = new User(us.getUsuario(), us.getContrasenna(), roles);
        return userDetails;
    }
}
