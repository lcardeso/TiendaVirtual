package com.example.demo.DTO;

import java.io.Serializable;

public class ActualizarGrupoDto implements Serializable {

    private Long idUsuario;
    private Long idGrupo;


    public ActualizarGrupoDto() {
    }

    public ActualizarGrupoDto(Long idUsuario, Long idGrupo) {
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return "ActualizarGrupoDto{" +
                "idUsuario=" + idUsuario +
                ", idGrupo=" + idGrupo +
                '}';
    }
}
