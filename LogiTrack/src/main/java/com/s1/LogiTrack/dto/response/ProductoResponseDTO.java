package com.s1.LogiTrack.dto.response;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        long id, String nombre, String categoria, String tamano, BigDecimal precioMensual
) {
}
