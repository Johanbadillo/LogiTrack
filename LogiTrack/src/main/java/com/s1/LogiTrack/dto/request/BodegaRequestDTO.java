package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


public record BodegaRequestDTO(
        @NotBlank(message = "No se permite valores vacios o solo espacioes en este campo")
        @Size(min = 2,max = 50,message = "El nombre debe tener un minimo de 2 a 50 caracteres")
        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        String nombre,
        @NotBlank(message = "No se permite valores vacios o solo espacioes en este campo")
        @Size(min = 2,max = 100,message = "El nombre debe tener un minimo de 2 a 100 caracteres")
        @Schema(description = "Ubicación física de la bodega", example = "Bogotá - Zona Industrial")
        String ubicacion,
        @NotNull(message = "No se permite valores vacios o solo espacioes en este campo")
        @PositiveOrZero(message = "La capacidad solo puede ser 0 o positivo, no se acepta numeros negativos")
        @Schema(description = "Capacidad Disponible de almacenamiento de la bodega", example = "500")
        Integer capacidad,
        @NotNull(message = "No se permite valores vacios o solo espacioes en este campo")
        @Positive(message = "La capacidad solo puede ser positivo, no se acepta numeros negativos")
        @Schema(description = "ID del empleado encargado de la bodega", example = "3")
        Long idEncargado
) {
}
