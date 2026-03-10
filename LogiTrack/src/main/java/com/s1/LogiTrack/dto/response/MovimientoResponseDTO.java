package com.s1.LogiTrack.dto.response;

import java.util.Date;

public record MovimientoResponseDTO(
        Long id, Date fecha, String tipoMovimiento, EmpleadoResponseDTO idEmpleado, BodegaResponseDTO idBodegaOrigen, BodegaResponseDTO idBodegaDestino
) {
}
