package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {

    BodegaResponseDTO crear(BodegaRequestDTO dto);

    List<BodegaResponseDTO> listar();

    BodegaResponseDTO buscarPorId(Long id);

    BodegaResponseDTO actualizar(Long id, BodegaRequestDTO dto);

    void eliminar(Long id);
}
