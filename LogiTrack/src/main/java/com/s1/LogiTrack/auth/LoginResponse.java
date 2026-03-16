package com.s1.LogiTrack.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "Token de validación para acceder al sistema")
        String token,

        @Schema(description = "Rol del usuario autenticado", example = "ADMIN")
        String rol
) {
}
