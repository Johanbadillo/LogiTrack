package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.EmpleadoMapper;
import com.s1.LogiTrack.mapper.InventarioMapper;
import com.s1.LogiTrack.mapper.ProductoMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Inventario;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.InventarioRepository;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final EmpleadoMapper empleadoMapper;

    @Override
    public InventarioResponseDTO crear(@NonNull InventarioRequestDTO dto) {

        if (inventarioRepository.existsByIdBodega_IdAndIdProducto_Id(dto.idBodega(), dto.idProducto())) {
            throw new BusinessRuleException("Este producto ya existe en el inventario de esta bodega");
        }

        Bodega b = bodegaRepository.findById(dto.idBodega())
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("No existe el producto"));

        Inventario inv = inventarioMapper.DTOAentidad(dto, b, p);
        Inventario inv_insertado = inventarioRepository.save(inv);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());
        BodegaResponseDTO dtoB = bodegaMapper.entidadADTO(b, dtoE);
        ProductoResponseDTO dtoP = productoMapper.entidadADTO(p);

        return inventarioMapper.entidadADTO(inv_insertado, dtoB, dtoP);
    }

    @Override
    public List<InventarioResponseDTO> listar() {

        return inventarioRepository.findAll().stream()
                .map(dato -> {

                    Bodega b = dato.getIdBodega();
                    Producto p = dato.getIdProducto();
                    return inventarioMapper.entidadADTO(dato, bodegaMapper.entidadADTO(b, empleadoMapper.entidadADTO(b.getIdEncargado())), productoMapper.entidadADTO(p));
                })
                .toList();
    }

    @Override
    public InventarioResponseDTO buscarPorId(Long id) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario"));

        Bodega b = inv.getIdBodega();
        Producto p = inv.getIdProducto();
        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());

        return inventarioMapper.entidadADTO(inv, bodegaMapper.entidadADTO(b, dtoE), productoMapper.entidadADTO(p));
    }

    @Override
    public InventarioResponseDTO actualizar(Long id, @NonNull InventarioRequestDTO dto) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario a actualizar"));

        Bodega b = bodegaRepository.findById(dto.idBodega())
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("No existe el producto"));

        inventarioMapper.actualizarEntidadDesdeDTO(inv, dto, b, p);

        Inventario inv_actualizado = inventarioRepository.save(inv);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());
        BodegaResponseDTO dtoB = bodegaMapper.entidadADTO(b, dtoE);
        ProductoResponseDTO dtoP = productoMapper.entidadADTO(p);

        return inventarioMapper.entidadADTO(inv_actualizado, dtoB, dtoP);
    }

    @Override
    public void eliminar(Long id) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario a eliminar"));

        inventarioRepository.delete(inv);
    }

}
