package com.s1.LogiTrack.dto.request;

import jakarta.validation.constraints.*;

public record BodegaRequestDTO(
        @NotBlank(message = "No se permite valores vacios o solo espacioes en este campo")
        @Size(min = 2,max = 50,message = "El nombre debe tener un minimo de 2 a 50 caracteres")
        String nombre,
        @NotBlank(message = "No se permite valores vacios o solo espacioes en este campo")
        @Size(min = 2,max = 100,message = "El nombre debe tener un minimo de 2 a 100 caracteres")
        String ubicacion,
        @NotNull(message = "No se permite valores vacios o solo espacioes en este campo")
        @PositiveOrZero(message = "La capacidad solo puede ser 0 o positivo, no se acepta numeros negativos")
        Integer capacidad,
        @NotNull(message = "No se permite valores vacios o solo espacioes en este campo")
        @Positive(message = "La capacidad solo puede ser positivo, no se acepta numeros negativos")
        Long idEncargado
) {
}
