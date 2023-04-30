package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    //Maneja las contrasennas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

/*    @Bean
    public UserDetailsService detailsService() {
        var user = User.withUsername("lis")
                .password("123")
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcrypt);
    }

    /**
     * Este  metodo le he puesto parametros en dependencia de los roles sin cargarlos de la entidad roles
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/home").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }



  /*  public static void main(String[] args) {
        System.out.println("pass:  " + new BCryptPasswordEncoder().encode("123"));
    }*/
}
