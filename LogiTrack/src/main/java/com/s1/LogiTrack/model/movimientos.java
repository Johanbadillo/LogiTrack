package com.s1.LogiTrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private  String tipoMovimiento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private empleado idEmpleado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private bodega idBodegaOrigen;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private bodega idBodegaDestino;

}
