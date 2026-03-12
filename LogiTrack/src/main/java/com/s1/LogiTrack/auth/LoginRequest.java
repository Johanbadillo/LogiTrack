package com.s1.LogiTrack.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
        @Schema(description = "Nombre de usuario del empleado", example = "juan_admin")
        String usuario,
        @NotBlank(message = "Esta campo no puede estar vacio o solo con espacios")
        @Schema(description = "Contraseña del usuario con al menos una mayúscula, una minúscula y un número", example = "Admin1234")
        String contrasena
) {
}
