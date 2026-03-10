package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record MovimientoRequestDTO(

        @NotNull(message = "La fecha del movimiento es obligatoria")
        Date fecha,
        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimiento tipoMovimiento,
        @NotNull(message = "El id del empleado es obligatorio")
        @Positive(message = "El id del empleado debe ser mayor que 0")
        Long idEmpleado,
        @NotNull(message = "El id de la bodega origen es obligatorio")
        @Positive(message = "El id de la bodega origen debe ser mayor que 0")
        Long idBodegaOrigen,
        @NotNull(message = "El id de la bodega destino es obligatorio")
        @Positive(message = "El id de la bodega destino debe ser mayor que 0")
        Long idBodegaDestino
) {
}
