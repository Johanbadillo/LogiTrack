package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.Rol;

public record EmpleadoResponseDTO(
        Long id,String nombre, String documento, String correo,String telefono, Rol rol
) {
}
