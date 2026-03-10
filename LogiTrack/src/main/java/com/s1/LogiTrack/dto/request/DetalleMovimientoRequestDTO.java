package com.s1.LogiTrack.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleMovimientoRequestDTO(

        @NotNull(message = "El id del movimiento es obligatorio")
        @Positive(message = "El id del movimiento debe ser mayor que 0")
        Long idMovimiento,
        @NotNull(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser mayor que 0")
        Long idProducto,
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 10, message = "La cantidad debe ser mínimo 10")
        Integer cantidad
) {
}
