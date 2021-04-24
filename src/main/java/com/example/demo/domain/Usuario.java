package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "usuario_table")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;
/**
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_usuario_FK", referencedColumnName = "id_grupo_usuario")
    private GrupoUsuario grupoUsuario;
    --------------------- Error cuando llamo al metodo Obtener todos ----------------------------
    Hibernate: select usuario0_.id_usuario as id_usuar1_2_, usuario0_.id_direccion_fk as id_direc8_2_, usuario0_.edad as edad2_2_, usuario0_.fecha_nacimiento as fecha_na3_2_, usuario0_.id_grupo_usuario_fk as id_grupo9_2_, usuario0_.nombre as nombre4_2_, usuario0_.primer_apellido as primer_a5_2_, usuario0_.segundo_apellido as segundo_6_2_, usuario0_.sexo as sexo7_2_ from usuario_table usuario0_
    Hibernate: select direccion0_.id_direccion as id_direc1_0_0_, direccion0_.calle as calle2_0_0_, direccion0_.codigo_postal as codigo_p3_0_0_ from direccion_table direccion0_ where direccion0_.id_direccion=?
    2021-04-22 21:54:41.572  WARN 7480 --- [nio-8106-exec-9] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: could not initialize proxy [com.example.demo.domain.GrupoUsuario#1] - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: could not initialize proxy [com.example.demo.domain.GrupoUsuario#1] - no Session (through reference chain: java.util.ArrayList[0]->com.example.demo.domain.Usuario["grupoUsuario"]->com.example.demo.domain.GrupoUsuario$HibernateProxy$PkNNgAcr["usuario"])]
*/

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_direccion_FK", referencedColumnName = "id_direccion")
    private Direccion direccion;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "primer_apellido")
    private String primApellido;

    @NotNull
    @Column(name = "segundo_apellido")
    private String segApellido;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechNac;

    @NotNull
    @Column(name = "edad")
    private Long edad;

    @NotNull
    @Column(name = "sexo")
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimApellido() {
        return primApellido;
    }

    public void setPrimApellido(String primApellido) {
        this.primApellido = primApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public LocalDateTime getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDateTime fechNac) {
        this.fechNac = fechNac;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

/*    public GrupoUsuario getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }
*/
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", direccion=" + direccion +
                ", nombre='" + nombre + '\'' +
                ", primApellido='" + primApellido + '\'' +
                ", segApellido='" + segApellido + '\'' +
                ", fechNac=" + fechNac +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}