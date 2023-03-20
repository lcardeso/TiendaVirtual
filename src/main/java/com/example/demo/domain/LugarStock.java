package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "lugar_stock", schema = "farmacia")
public class LugarStock extends  Domain implements Serializable {

    @OneToMany(mappedBy = "lugarStock")
    private List<StockMedicamento> stockMedicamentos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia_FK", referencedColumnName = "id_farmacia")
    private Farmacia farmacia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lugar_stock")
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "categoria")
    private String categoria;

    @NotNull
    @Column(name = "ubicacion")
    /** La ubicacion se crea a partir de la Fila (X) - Columna (Y) Ejemplo: 3-4 */
    private String ubicacion;


    public LugarStock stockMedicamentos(List<StockMedicamento> stockMedicamentos) {
        this.stockMedicamentos = stockMedicamentos;
        return this;
    }

    public LugarStock farmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
        return this;
    }

    public LugarStock id(Long id) {
        this.id = id;
        return this;
    }

    public LugarStock nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LugarStock categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public LugarStock ubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }
}