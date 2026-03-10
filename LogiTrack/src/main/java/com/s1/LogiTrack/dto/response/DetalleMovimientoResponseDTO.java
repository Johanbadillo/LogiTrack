package com.s1.LogiTrack.dto.response;

public record DetalleMovimientoResponseDTO(
        Long id, MovimientoResponseDTO idMovimiento, ProductoResponseDTO idProducto, Integer cantidad
) {
}
