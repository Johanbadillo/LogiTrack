package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    @Override
    public MovimientoResponseDTO crear(MovimientoRequestDTO dto) {
        return null;
    }

    @Override
    public List<MovimientoResponseDTO> listar() {
        return List.of();
    }

    @Override
    public MovimientoResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public MovimientoResponseDTO actualizar(Long id, MovimientoRequestDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
