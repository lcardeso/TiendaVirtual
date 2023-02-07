package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "stock_medicamento", schema = "farmacia")
public class StockMedicamento implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_medicamento_FK", referencedColumnName = "id_medicamento")
    private Medicamento medicamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_medicamento")
    private Long id;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;


    public StockMedicamento medicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        return this;
    }

    public StockMedicamento id(Long id) {
        this.id = id;
        return this;
    }

    public StockMedicamento cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }
}