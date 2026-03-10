package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.Tamano;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        long id, String nombre, String categoria, Tamano tamano, BigDecimal precioMensual
) {
}
