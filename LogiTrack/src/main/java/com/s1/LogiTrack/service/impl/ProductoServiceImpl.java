package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.ProductoMapper;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto p = productoMapper.DTOAentidad(dto);
        Producto productoInsertado = productoRepository.save(p);

        return productoMapper.entidadADTO(productoInsertado);
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::entidadADTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado"));

        return productoMapper.entidadADTO(p);
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el producto a actualizar"));

        productoMapper.actualizarEntidadDesdeDTO(p, dto);

        Producto productoActualizado = productoRepository.save(p);

        return productoMapper.entidadADTO(productoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el producto a eliminar"));

        productoRepository.delete(p);
    }
}
