package com.s1.LogiTrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Integer Capacidad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private empleado idEncargado;

}
