package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO crear(ProductoRequestDTO dto);

    List<ProductoResponseDTO> listar();

    ProductoResponseDTO buscarPorId(Long id);

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);
}
