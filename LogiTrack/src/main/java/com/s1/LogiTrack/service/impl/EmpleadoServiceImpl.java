package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.EmpleadoMapper;
import com.s1.LogiTrack.model.Empleado;
import com.s1.LogiTrack.repository.EmpleadoRepository;
import com.s1.LogiTrack.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        Empleado e = empleadoMapper.DTOAentidad(dto, pdto);
        Empleado e_insertado = empleadoRepository.save(e);

        return empleadoMapper.entidadADTO(e_insertado);
    }

    @Override
    public List<EmpleadoResponseDTO> listar() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::entidadADTO)
                .toList();
    }

    @Override
    public EmpleadoResponseDTO buscarPorId(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado"));
        return empleadoMapper.entidadADTO(e);
    }

    @Override
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado a actualizar"));
        empleadoMapper.actualizarEntidadDesdeDTO(e, dto, pdto);
        Empleado e_actualizado = empleadoRepository.save(e);
        return empleadoMapper.entidadADTO(e_actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado a eliminar"));
        empleadoRepository.delete(e);
    }
}
