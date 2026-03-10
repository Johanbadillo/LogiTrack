package com.s1.LogiTrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class empleado extends persona {

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;

    @ManyToMany(mappedBy = "idEncargado")
    private Collection<bodega> bodegas;

    public Collection<bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(Collection<bodega> bodegas) {
        this.bodegas = bodegas;
    }
}
