package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;

import java.util.List;

public interface DetalleMovimientoService {

    DetalleMovimientoResponseDTO crear(DetalleMovimientoRequestDTO dto);

    DetalleMovimientoResponseDTO buscarPorId(Long id);

    DetalleMovimientoResponseDTO actualizar(Long id, DetalleMovimientoRequestDTO dto);

    void eliminar(Long id);

    List<DetalleMovimientoResponseDTO> buscarPorMovimientoId(Long idMovimiento);
}
