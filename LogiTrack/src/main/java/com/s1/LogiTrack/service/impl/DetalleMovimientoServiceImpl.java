package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.*;
import com.s1.LogiTrack.model.*;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.DetalleMovimientoRepository;
import com.s1.LogiTrack.repository.InventarioRepository;
import com.s1.LogiTrack.repository.MovimientoRepository;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.DetalleMovimientoService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleMovimientoServiceImpl implements DetalleMovimientoService {

    private final DetalleMovimientoRepository detalleMovimientoRepository;
    private final DetalleMovimientoMapper detalleMovimientoMapper;

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final EmpleadoMapper empleadoMapper;
    private final BodegaMapper bodegaMapper;

    private final InventarioRepository inventarioRepository;
    private final BodegaRepository bodegaRepository;

    @Override
    public DetalleMovimientoResponseDTO crear(@NonNull DetalleMovimientoRequestDTO dto) {

        Movimiento m = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO MOVIMIENTO"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO PRODUCTO"));

        Bodega origen = m.getIdBodegaOrigen();
        Bodega destino = m.getIdBodegaDestino();

        // ── Validar que el producto existe en el inventario origen ──
        Inventario invOrigen = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(origen.getId(), p.getId());

        if (invOrigen == null) {
            throw new BusinessRuleException("EL PRODUCTO NO EXISTE EN LA BODEGA ORIGEN");
        }
        if (invOrigen.getCantidad() < dto.cantidad()) {
            throw new BusinessRuleException("NO HAY STOCK SUFICIENTE EN BODEGA ORIGEN. " +
                    "Stock actual: " + invOrigen.getCantidad());
        }

        if (destino.getCapacidad() < dto.cantidad()) {
            throw new BusinessRuleException("NO HAY ESPACIO SUFICIENTE EN BODEGA DESTINO. " +
                    "Espacio disponible: " + destino.getCapacidad());
        }

        invOrigen.setCantidad(invOrigen.getCantidad() - dto.cantidad());
        inventarioRepository.save(invOrigen);

        origen.setCapacidad(origen.getCapacidad() + dto.cantidad());
        bodegaRepository.save(origen);

        destino.setCapacidad(destino.getCapacidad() - dto.cantidad());
        bodegaRepository.save(destino);

        Inventario invDestino = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(destino.getId(), p.getId());

        if (invDestino != null) {
            invDestino.setCantidad(invDestino.getCantidad() + dto.cantidad());
            inventarioRepository.save(invDestino);
        } else {
            Inventario nuevo = new Inventario();
            nuevo.setIdBodega(destino);
            nuevo.setIdProducto(p);
            nuevo.setCantidad(dto.cantidad());
            inventarioRepository.save(nuevo);
        }

        DetalleMovimiento d = detalleMovimientoMapper.DTOAentidad(dto, m, p);
        DetalleMovimiento dInsertado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(origen, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(destino, dtoE);

        return detalleMovimientoMapper.entidadADTO(
                dInsertado,
                movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(p)
        );
    }

    @Override
    public DetalleMovimientoResponseDTO buscarPorId(Long id) {

        DetalleMovimiento d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO DETALLE MOVIMIENTO"));

        Movimiento m = d.getIdMovimiento();
        Producto p = d.getIdProducto();

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

        return detalleMovimientoMapper.entidadADTO(
                d,
                movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(p)
        );
    }

    @Override
    public DetalleMovimientoResponseDTO actualizar(Long id, @NonNull DetalleMovimientoRequestDTO dto) {

        DetalleMovimiento d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DETALLE MOVIMIENTO"));

        Movimiento mAnterior = d.getIdMovimiento();
        Producto pAnterior = d.getIdProducto();
        int cantidadAnterior = d.getCantidad();
        Bodega origenAnterior = mAnterior.getIdBodegaOrigen();
        Bodega destinoAnterior = mAnterior.getIdBodegaDestino();

        Inventario invOrigenAnterior = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(origenAnterior.getId(), pAnterior.getId());

        if (invOrigenAnterior != null) {
            invOrigenAnterior.setCantidad(invOrigenAnterior.getCantidad() + cantidadAnterior);
            inventarioRepository.save(invOrigenAnterior);
        }

        Inventario invDestinoAnterior = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(destinoAnterior.getId(), pAnterior.getId());

        if (invDestinoAnterior != null) {
            invDestinoAnterior.setCantidad(invDestinoAnterior.getCantidad() - cantidadAnterior);
            inventarioRepository.save(invDestinoAnterior);
        }

        origenAnterior.setCapacidad(origenAnterior.getCapacidad() - cantidadAnterior);
        bodegaRepository.save(origenAnterior);

        destinoAnterior.setCapacidad(destinoAnterior.getCapacidad() + cantidadAnterior);
        bodegaRepository.save(destinoAnterior);

        Movimiento mNuevo = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE MOVIMIENTO"));

        Producto pNuevo = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE PRODUCTO"));

        Bodega origenNuevo = mNuevo.getIdBodegaOrigen();
        Bodega destinoNuevo = mNuevo.getIdBodegaDestino();

        Inventario invOrigenNuevo = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(origenNuevo.getId(), pNuevo.getId());

        if (invOrigenNuevo == null) {
            throw new BusinessRuleException("EL PRODUCTO NO EXISTE EN LA BODEGA ORIGEN");
        }
        if (invOrigenNuevo.getCantidad() < dto.cantidad()) {
            throw new BusinessRuleException("NO HAY STOCK SUFICIENTE EN BODEGA ORIGEN. " +
                    "Stock actual: " + invOrigenNuevo.getCantidad());
        }
        if (destinoNuevo.getCapacidad() < dto.cantidad()) {
            throw new BusinessRuleException("NO HAY ESPACIO SUFICIENTE EN BODEGA DESTINO. " +
                    "Espacio disponible: " + destinoNuevo.getCapacidad());
        }

        invOrigenNuevo.setCantidad(invOrigenNuevo.getCantidad() - dto.cantidad());
        inventarioRepository.save(invOrigenNuevo);

        origenNuevo.setCapacidad(origenNuevo.getCapacidad() + dto.cantidad());
        bodegaRepository.save(origenNuevo);

        destinoNuevo.setCapacidad(destinoNuevo.getCapacidad() - dto.cantidad());
        bodegaRepository.save(destinoNuevo);

        Inventario invDestinoNuevo = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(destinoNuevo.getId(), pNuevo.getId());

        if (invDestinoNuevo != null) {
            invDestinoNuevo.setCantidad(invDestinoNuevo.getCantidad() + dto.cantidad());
            inventarioRepository.save(invDestinoNuevo);
        } else {
            Inventario nuevo = new Inventario();
            nuevo.setIdBodega(destinoNuevo);
            nuevo.setIdProducto(pNuevo);
            nuevo.setCantidad(dto.cantidad());
            inventarioRepository.save(nuevo);
        }

        detalleMovimientoMapper.actualizarEntidadDesdeDTO(d, dto, mNuevo, pNuevo);
        DetalleMovimiento actualizado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(mNuevo.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(origenNuevo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(destinoNuevo, dtoE);

        return detalleMovimientoMapper.entidadADTO(
                actualizado,
                movimientoMapper.entidadADTO(mNuevo, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(pNuevo)
        );
    }

    @Override
    public void eliminar(Long id) {

        DetalleMovimiento d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DETALLE MOVIMIENTO"));

        detalleMovimientoRepository.delete(d);
    }

    @Override
    public List<DetalleMovimientoResponseDTO> buscarPorMovimientoId(Long idMovimiento) {

        return detalleMovimientoRepository.findByIdMovimientoId(idMovimiento).stream()
                .map(dato -> {

                    Movimiento m = dato.getIdMovimiento();
                    Producto p = dato.getIdProducto();

                    EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
                    BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
                    BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

                    return detalleMovimientoMapper.entidadADTO(
                            dato,
                            movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                            productoMapper.entidadADTO(p)
                    );
                })
                .toList();
    }
}