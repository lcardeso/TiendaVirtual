package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@Data
@Entity
@Table(name = "venta", schema = "concesionario")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_automovil_FK", referencedColumnName = "id_automovil")
    private Automovil automovil;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_persona_FK", referencedColumnName = "id_persona")
    private Persona persona;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_metodo_pago_FK", referencedColumnName = "id_metodo_pago")
    private MetodoDePago metodoPago;

    @NotNull
    @Column(name = "precio_venta")
    private String precioVenta;

    @NotNull
    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;


    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", metodoPago=" + metodoPago +
                ", precioVenta='" + precioVenta + '\'' +
                ", fechaVenta=" + fechaVenta +
                '}';
    }
}