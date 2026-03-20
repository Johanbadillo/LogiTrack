package com.s1.LogiTrack.dto.response;

public record ReporteMovimientoResponseDTO(
        Long cantidadMovimiento,
        Long totalMovimientosEntrada,
        Long totalMovimientosSalida,
        Long totalMovimientosTransferencia

) {
}
