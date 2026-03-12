package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.EmpleadoMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.EmpleadoRepository;
import com.s1.LogiTrack.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor

public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public BodegaResponseDTO crear(BodegaRequestDTO dto) {
        Empleado e = empleadoRepository.findById(dto.idEncargado())
                .orElseThrow(() -> new BusinessRuleException("Error, no existe dicho encargado"));
        Bodega b=bodegaMapper.DTOAentidad(dto,e);
        Bodega bodega_insertada=bodegaRepository.save(b);

        EmpleadoResponseDTO dtoE=empleadoMapper.entidadADTO(e);
        return bodegaMapper.entidadADTO(bodega_insertada,dtoE);
    }

    @Override
    public List<BodegaResponseDTO> listar() {
        return bodegaRepository.findAll().stream()
                .map(dato->{
                    Empleado e = dato.getIdEncargado();
                    return bodegaMapper.entidadADTO(dato, empleadoMapper.entidadADTO(e));
                })
                .toList();
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        Bodega b=bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicha bodega"));
        Empleado e=empleadoRepository.findById(b.getIdEncargado().getId())
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado"));
        return bodegaMapper.entidadADTO(b,empleadoMapper.entidadADTO(e));
    }

    @Override
    public BodegaResponseDTO actualizar(Long id, BodegaRequestDTO dto) {
        Bodega b=bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Error, no existe dicha bodega a actualizar"));
        Empleado e=empleadoRepository.findById(dto.idEncargado())
                .orElseThrow(() -> new BusinessRuleException("Error, no existe dicho empleado"));
        bodegaMapper.actualizarEntidadDesdeDTO(b,dto,e);
        Bodega bodega_actualizada=bodegaRepository.save(b);

        EmpleadoResponseDTO dtoE=empleadoMapper.entidadADTO(e);
        return bodegaMapper.entidadADTO(bodega_actualizada,dtoE);
    }

    @Override
    public void eliminar(Long id) {
        Bodega b=bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicha bodega a eliminar"));
        bodegaRepository.delete(b);
    }

}
