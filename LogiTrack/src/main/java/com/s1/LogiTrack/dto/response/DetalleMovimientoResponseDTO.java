package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetalleMovimientoResponseDTO(
        @Schema(description = "ID del detalle del movimiento", example = "1")
        Long id,

        @Schema(description = "Información del movimiento asociado")
        MovimientoResponseDTO idMovimiento,

        @Schema(description = "Información del producto asociado al movimiento")
        ProductoResponseDTO idProducto,

        @Schema(description = "Cantidad del producto en el movimiento", example = "50")
        Integer cantidad
) {
}
