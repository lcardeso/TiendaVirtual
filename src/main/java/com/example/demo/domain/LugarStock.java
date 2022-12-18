package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "lugar_stock", schema = "farmacia")
public class LugarStock implements Serializable {

    @OneToMany
    private List<StockMedicamento> stockMedicamentos;

   /* @ManyToOne
    @JoinColumn(name = "id_farmacia_FK", referencedColumnName = "id_farmacia")
    private Farmacia farmacia;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lugar_stock")
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

   }