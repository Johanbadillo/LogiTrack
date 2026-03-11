package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.DetalleMovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.DetalleMovimientoResponseDTO;
import com.s1.LogiTrack.service.DetalleMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleMovimientoServiceImpl implements DetalleMovimientoService {

    @Override
    public DetalleMovimientoResponseDTO crear(DetalleMovimientoRequestDTO dto) {
        return null;
    }

    @Override
    public DetalleMovimientoResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public DetalleMovimientoResponseDTO actualizar(Long id, DetalleMovimientoRequestDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<DetalleMovimientoResponseDTO> buscarPorMovimientoId(Long idMovimiento) {
        return List.of();
    }

}
