package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.model.DetalleMovimiento;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class DetalleMovimientoMapper {

    public DetalleMovimientoResponseDTO entidadADTO(DetalleMovimiento detalle, MovimientoResponseDTO dtoM, ProductoResponseDTO dtoP) {
        if (detalle == null || dtoM == null || dtoP == null) return null;

        return new DetalleMovimientoResponseDTO(
                detalle.getId(),
                dtoM,
                dtoP,
                detalle.getCantidad()
        );
    }

    public DetalleMovimiento DTOAentidad(DetalleMovimientoRequestDTO dto, Movimiento movimiento, Producto producto) {
        if (dto == null || movimiento == null || producto == null) return null;

        DetalleMovimiento d = new DetalleMovimiento();
        d.setIdMovimiento(movimiento);
        d.setIdProducto(producto);
        d.setCantidad(dto.cantidad());

        return d;
    }

    public void actualizarEntidadDesdeDTO(DetalleMovimiento detalle, DetalleMovimientoRequestDTO dto, Movimiento movimiento, Producto producto) {
        if (detalle == null || dto == null || movimiento == null || producto == null) return;

        detalle.setIdMovimiento(movimiento);
        detalle.setIdProducto(producto);
        detalle.setCantidad(dto.cantidad());
    }

}
