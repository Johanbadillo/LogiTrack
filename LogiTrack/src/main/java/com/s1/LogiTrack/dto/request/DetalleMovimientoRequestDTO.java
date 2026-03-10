package com.s1.LogiTrack.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DetalleMovimientoRequestDTO(

        @NotBlank(message = "El id del movimiento es obligatorio")
        @Positive(message = "El id del movimiento debe ser mayor que 0")
        Long idMovimiento,
        @NotBlank(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser mayor que 0")
        Long idProducto,
        @NotBlank(message = "La cantidad es obligatoria")
        @Min(value = 10, message = "La cantidad debe ser mínimo 10")
        Integer cantidad
) {
}
