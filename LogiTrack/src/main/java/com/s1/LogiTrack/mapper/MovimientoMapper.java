package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.model.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public MovimientoResponseDTO entidadADTO(Movimiento movimiento, EmpleadoResponseDTO dtoE, BodegaResponseDTO dtoBO, BodegaResponseDTO dtoBD) {
        if (movimiento == null || dtoE == null || dtoBO == null || dtoBD == null) return null;

        return new MovimientoResponseDTO(
                movimiento.getId(),
                movimiento.getFecha(),
                movimiento.getTipoMovimiento().toString(),
                dtoE,
                dtoBO,
                dtoBD
        );
    }

    public Movimiento DTOAentidad(MovimientoRequestDTO dto, Empleado empleado, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (dto == null || empleado == null || bodegaOrigen == null || bodegaDestino == null) return null;

        Movimiento m = new Movimiento();
        m.setFecha(dto.fecha());
        m.setTipoMovimiento(dto.tipoMovimiento());
        m.setIdEmpleado(empleado);
        m.setIdBodegaOrigen(bodegaOrigen);
        m.setIdBodegaDestino(bodegaDestino);

        return m;
    }

    public void actualizarEntidadDesdeDTO(Movimiento movimiento, MovimientoRequestDTO dto, Empleado empleado, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (movimiento == null || dto == null || empleado == null || bodegaOrigen == null || bodegaDestino == null)
            return;

        movimiento.setFecha(dto.fecha());
        movimiento.setTipoMovimiento(dto.tipoMovimiento());
        movimiento.setIdEmpleado(empleado);
        movimiento.setIdBodegaOrigen(bodegaOrigen);
        movimiento.setIdBodegaDestino(bodegaDestino);
    }

}
