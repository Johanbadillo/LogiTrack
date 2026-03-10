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
public class inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private bodega idBodega;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private producto idProducto;

    @Column(nullable = false)
    private Integer cantidad;

}
