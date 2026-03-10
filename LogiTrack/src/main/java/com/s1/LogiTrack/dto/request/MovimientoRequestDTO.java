package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.TipoMovimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record MovimientoRequestDTO(

        @NotBlank(message = "La fecha del movimiento es obligatoria")
        Date fecha,
        @NotBlank(message = "El tipo de movimiento es obligatorio")
        TipoMovimiento tipoMovimiento,
        @NotBlank(message = "El id del empleado es obligatorio")
        @Positive(message = "El id del empleado debe ser mayor que 0")
        Long idEmpleado,
        @NotBlank(message = "El id de la bodega origen es obligatorio")
        @Positive(message = "El id de la bodega origen debe ser mayor que 0")
        Long idBodegaOrigen,
        @NotBlank(message = "El id de la bodega destino es obligatorio")
        @Positive(message = "El id de la bodega destino debe ser mayor que 0")
        Long idBodegaDestino
) {
}
