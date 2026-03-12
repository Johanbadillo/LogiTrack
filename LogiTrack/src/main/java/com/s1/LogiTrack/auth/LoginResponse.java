package com.s1.LogiTrack.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "Este es el token de validacion para poder ingresar a los datos")
        String token
) {
}
