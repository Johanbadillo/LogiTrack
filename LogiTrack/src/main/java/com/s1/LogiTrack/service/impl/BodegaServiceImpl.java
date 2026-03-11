package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    @Override
    public BodegaResponseDTO crear(BodegaRequestDTO dto) {
        return null;
    }

    @Override
    public List<BodegaResponseDTO> listar() {
        return List.of();
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public BodegaResponseDTO actualizar(Long id, BodegaRequestDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

}
