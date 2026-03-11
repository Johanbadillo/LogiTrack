package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        return null;
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return List.of();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
