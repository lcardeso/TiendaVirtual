package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_table")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_fk", referencedColumnName = "id_grupo")
    private Grupo grupo;


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