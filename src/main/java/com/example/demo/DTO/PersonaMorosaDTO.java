package com.example.demo.DTO;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonaMorosaDTO implements Serializable {

    private Integer total;
    private List<PersonaDTO> personas = new ArrayList<>();


    public PersonaMorosaDTO total(Integer total) {
        this.total = total;
        return this;
    }

    public PersonaMorosaDTO personas(List<PersonaDTO> personas) {
        this.personas = personas;
        return this;
    }
}