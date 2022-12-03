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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pago_fin_FK", referencedColumnName = "id_pago_financiado")
    private PagoFinanciado pagoFinanciado;

    @NotNull
    @Column(name = "precio_venta")
    private Double precioVenta;

    @NotNull
    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @NotNull
    @Column(name = "estado_venta")
    private String estadoVenta;


    public Venta id(Long id) {
        this.id = id;
        return this;
    }

    public Venta automovil(Automovil automovil) {
        this.automovil = automovil;
        return this;
    }

    public Venta persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public Venta metodoPago(MetodoDePago metodoPago) {
        this.metodoPago = metodoPago;
        return this;
    }

    public Venta pagoFinanciado(PagoFinanciado pagoFinanciado) {
        this.pagoFinanciado = pagoFinanciado;
        return this;
    }

    public Venta precioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
        return this;
    }

    public Venta fechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
        return this;
    }

    public Venta estadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
        return this;
    }
}