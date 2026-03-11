package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmpleadoResponseDTO(
        @Schema(description = "ID del empleado", example = "3")
        Long id,

        @Schema(description = "Nombre completo del empleado", example = "Juan Pérez")
        String nombre,

        @Schema(description = "Número de documento del empleado", example = "1023456789")
        String documento,

        @Schema(description = "Correo electrónico del empleado", example = "juan.perez@logitrack.com")
        String correo,

        @Schema(description = "Número de teléfono del empleado", example = "3001234567")
        String telefono,

        @Schema(description = "Rol del empleado dentro del sistema", example = "ADMIN")
        Rol rol
) {
}
