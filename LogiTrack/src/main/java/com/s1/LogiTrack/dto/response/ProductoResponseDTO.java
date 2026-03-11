package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.Tamano;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        @Schema(description = "ID del producto", example = "3")
        Long id,

        @Schema(description = "Nombre del producto", example = "Caja de almacenamiento")
        String nombre,

        @Schema(description = "Categoría del producto", example = "Logística")
        String categoria,

        @Schema(description = "Tamaño del producto", example = "MEDIANO")
        Tamano tamano,

        @Schema(description = "Precio mensual del producto", example = "120000")
        BigDecimal precioMensual
) {
}
