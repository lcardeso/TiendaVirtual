package com.example.demo.DTO;

import com.example.demo.domain.PermisosAsociados;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class RolDTO implements Serializable {

    private String descripcion;
    List<PermisosAsociados> permAsociados;




}