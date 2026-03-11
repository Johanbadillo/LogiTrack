package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;

import java.util.List;

public interface MovimientoService {
    MovimientoResponseDTO crear(MovimientoRequestDTO dto);

    List<MovimientoResponseDTO> listar();

    MovimientoResponseDTO buscarPorId(Long id);

    MovimientoResponseDTO actualizar(Long id, MovimientoRequestDTO dto);

    void eliminar(Long id);
}
