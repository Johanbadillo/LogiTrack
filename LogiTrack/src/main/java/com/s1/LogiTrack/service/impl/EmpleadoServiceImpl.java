package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.EmpleadoRequestDTO;
import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.EmpleadoResponseDTO;
import com.s1.LogiTrack.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    @Override
    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        return null;
    }

    @Override
    public List<EmpleadoResponseDTO> listar() {
        return List.of();
    }

    @Override
    public EmpleadoResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto, PersonaRequestDTO pdto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
