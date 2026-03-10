package com.s1.LogiTrack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record InventarioRequestDTO(

        @NotBlank(message = "El id de la bodega es obligatorio")
        @Positive(message = "El id de la bodega debe ser mayor que 0")
        Long idBodega,

        @NotBlank(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser mayor que 0")
        Long idProducto,

        @NotBlank(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que 0")
        Integer cantidad
) {
}
