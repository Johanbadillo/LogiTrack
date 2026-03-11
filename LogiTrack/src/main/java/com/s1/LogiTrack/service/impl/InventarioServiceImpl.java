package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    @Override
    public InventarioResponseDTO crear(InventarioRequestDTO dto) {
        return null;
    }

    @Override
    public List<InventarioResponseDTO> listar() {
        return List.of();
    }

    @Override
    public InventarioResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public InventarioResponseDTO actualizar(Long id, InventarioRequestDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
