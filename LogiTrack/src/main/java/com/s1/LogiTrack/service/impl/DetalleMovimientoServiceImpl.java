package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.*;
import com.s1.LogiTrack.model.DetalleMovimiento;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.DetalleMovimientoRepository;
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

    @Override
    public DetalleMovimientoResponseDTO crear(@NonNull DetalleMovimientoRequestDTO dto) {

        Movimiento m = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO MOVIMIENTO"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DICHO PRODUCTO"));

        DetalleMovimiento d = detalleMovimientoMapper.DTOAentidad(dto, m, p);
        DetalleMovimiento dInsertado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

        return detalleMovimientoMapper.entidadADTO(dInsertado, movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD), productoMapper.entidadADTO(p)
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

        return detalleMovimientoMapper.entidadADTO(d, movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD), productoMapper.entidadADTO(p));
    }

    @Override
    public DetalleMovimientoResponseDTO actualizar(Long id, @NonNull DetalleMovimientoRequestDTO dto) {

        DetalleMovimiento d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE DETALLE MOVIMIENTO"));

        Movimiento m = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE MOVIMIENTO"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("NO EXISTE PRODUCTO"));

        detalleMovimientoMapper.actualizarEntidadDesdeDTO(d, dto, m, p);
        DetalleMovimiento actualizado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

        return detalleMovimientoMapper.entidadADTO(actualizado, movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD), productoMapper.entidadADTO(p)
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

                    return detalleMovimientoMapper.entidadADTO(dato, movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD), productoMapper.entidadADTO(p));
                })
                .toList();
    }

}
