package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Inventario;
import com.s1.LogiTrack.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioResponseDTO entidadADTO(Inventario inventario, BodegaResponseDTO dtoB, ProductoResponseDTO dtoP) {
        if (inventario == null || dtoB == null || dtoP == null) return null;

        return new InventarioResponseDTO(
                inventario.getId(),
                dtoB,
                dtoP,
                inventario.getCantidad()
        );
    }

    public Inventario DTOAentidad(InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (dto == null || bodega == null || producto == null) return null;

        Inventario i = new Inventario();
        i.setIdBodega(bodega);
        i.setIdProducto(producto);
        i.setCantidad(dto.cantidad());

        return i;
    }

    public void actualizarEntidadDesdeDTO(Inventario inventario, InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (inventario == null || dto == null || bodega == null || producto == null) return;

        inventario.setIdBodega(bodega);
        inventario.setIdProducto(producto);
        inventario.setCantidad(dto.cantidad());
    }
}
