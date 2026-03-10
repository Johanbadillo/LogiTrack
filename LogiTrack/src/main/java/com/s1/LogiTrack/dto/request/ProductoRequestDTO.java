package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.Tamano;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductoRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,
        @NotBlank(message = "La categoria no puede estar vacía")
        @Size(min = 3, max = 30, message = "La categoria debe tener entre 3 y 30 caracteres")
        String categoria,
        @NotBlank(message = "El tamaño no puede estar vacío")
        Tamano tamano,
        @NotBlank(message = "El precio mensual no puede ser nulo")
        @Positive( message = "El precio debe ser mayor que 0")
        BigDecimal precioMensual
) {
}
