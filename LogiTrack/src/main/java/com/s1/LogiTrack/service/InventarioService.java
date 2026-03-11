package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {

    InventarioResponseDTO crear(InventarioRequestDTO dto);

    List<InventarioResponseDTO> listar();

    InventarioResponseDTO buscarPorId(Long id);

    InventarioResponseDTO actualizar(Long id, InventarioRequestDTO dto);

    void eliminar(Long id);
}
